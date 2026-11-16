package com.ahmad.pocketmarket.data

import com.ahmad.pocketmarket.domain.model.AppError
import com.ahmad.pocketmarket.domain.model.AppResult
import com.ahmad.pocketmarket.domain.model.Category
import com.ahmad.pocketmarket.domain.model.Product
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import java.io.IOException

data class CatalogSnapShot(
    val categories : List<Category>,
    val products: List<Product>,
    val featured: List<Product>,

    )
data class PartialSnapshot(
    val categories: AppResult<List<Category>>,
    val products: AppResult<List<Product>>,
    val featured: AppResult<List<Product>>,
)

class CatalogLoader(
    private val api: CatalogApi,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend fun loadSequential(): CatalogSnapShot = withContext(dispatcher) {
        val categories = api.getCategories()
        val products = api.getProduct()
        val featured = api.getFeatured()
        CatalogSnapShot(categories, products, featured)
    }

    suspend fun loadParallel(): CatalogSnapShot = withContext(dispatcher) {
        coroutineScope {
            val categories = async { api.getCategories() }
            val products = async { api.getProduct() }
            val featured = async { api.getFeatured() }
            CatalogSnapShot(categories.await(), products.await(), featured.await())
        }
    }

    suspend fun loadPartial(): PartialSnapshot = withContext(dispatcher) {
        supervisorScope {
            val categories = async { safeCall { api.getCategories() } }
            val products = async { safeCall { api.getProduct() } }
            val featured = async { safeCall { api.getFeatured() } }
            PartialSnapshot(categories.await(), products.await(), featured.await())
        }
    }

    private suspend fun <T> safeCall(block: suspend () -> T): AppResult<T> = try {
        AppResult.Success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: IOException) {
        AppResult.Failure(AppError.NoInternet)
    } catch (e: Exception) {
        AppResult.Failure(AppError.Unknown(e.message))
    }
}

