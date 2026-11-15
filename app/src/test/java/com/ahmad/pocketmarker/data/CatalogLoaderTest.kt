
package com.ahmad.pocketmarker.data

import com.ahmad.pocketmarker.domain.model.AppResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import java.io.IOException
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CatalogLoaderTest {

    @Test
    fun sequential_takes_sum_of_delays() = runTest {
        val loader = CatalogLoader(FakeCatalogApi(500), StandardTestDispatcher(testScheduler))
        loader.loadSequential()
        assertEquals(1500L, testScheduler.currentTime)
    }

    @Test
    fun parallel_takes_longest_delay_only() = runTest {
        val loader = CatalogLoader(FakeCatalogApi(500), StandardTestDispatcher(testScheduler))
        loader.loadParallel()
        assertEquals(500L, testScheduler.currentTime)
    }

    @Test
    fun parallel_fails_fast_when_one_call_fails() = runTest {
        val api = FakeCatalogApi(500, failOn = setOf("products"))
        val loader = CatalogLoader(api, StandardTestDispatcher(testScheduler))
        assertFailsWith<IOException> { loader.loadParallel() }
    }
    @Test
    fun partial_returns_success_and_failure_together() = runTest {
        val api = FakeCatalogApi(500, failOn = setOf("products"))
        val loader = CatalogLoader(api, StandardTestDispatcher(testScheduler))
        val result = loader.loadPartial()
        assertTrue(result.categories is AppResult.Success, "categories = ${result.categories}")
        assertTrue(result.products is AppResult.Failure, "products = ${result.products}")
        assertTrue(result.featured is AppResult.Success, "featured = ${result.featured}")
    }

    @Test
    fun cancellation_stops_in_flight_work() = runTest {
        val api = FakeCatalogApi(500)
        val loader = CatalogLoader(api, StandardTestDispatcher(testScheduler))
        val job = launch { loader.loadParallel() }
        advanceTimeBy(100)
        job.cancelAndJoin()
        assertTrue(job.isCancelled)
        assertEquals(0, api.completedCalls)
    }
}