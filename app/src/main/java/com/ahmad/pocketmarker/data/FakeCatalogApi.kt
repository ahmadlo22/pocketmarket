package com.ahmad.pocketmarker.data

import com.ahmad.pocketmarker.domain.fixtures.ProductFixtures
import com.ahmad.pocketmarker.domain.model.Category
import com.ahmad.pocketmarker.domain.model.Product
import kotlinx.coroutines.delay
import java.io.IOException

class FakeCatalogApi(
    private val delayMS : Long = 500,
    private  val failOn : Set<String> = emptySet(),
    ) : CatalogApi{
    var completedCalls = 0
        private set

    private suspend fun <T> call(name: String,block : () -> T):T{
        delay(delayMS)
        if(name in failOn) throw IOException("$name failed")
        return block().also { completedCalls++ }
    }

    override suspend fun getCategories(): List<Category> =
        call("categories") { ProductFixtures.categories }

    override suspend fun getProduct(): List<Product> =
        call("products") { ProductFixtures.product() }

    override suspend fun getFeatured(): List<Product> =
        call("featured") { ProductFixtures.product().take(5) }



}



