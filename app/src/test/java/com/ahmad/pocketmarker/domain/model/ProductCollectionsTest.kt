package com.ahmad.pocketmarker.domain.model

import com.ahmad.pocketmarker.domain.fixtures.ProductFixtures
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
class ProductCollectionsTest{
    private  val product = ProductFixtures.product()

    @Test
    fun filter_by_category_returns_only_matching() {
        val result = product.filterByCategory("laptops")
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.category.slug == "laptops" })
    }

    @Test
    fun null_category_returns_everything() {
        assertEquals(product.size, product.filterByCategory(null).size)
    }
    @Test
    fun search_is_case_insensitive_and_trims() {
        assertEquals(1, product.search("  PRODUCT 7 ").size)
    }

    @Test
    fun sort_price_ascending() {
        val prices = product.applySort(SortOption.PRICE_ASC).map { it.priceCents }
        assertEquals(prices.sorted(), prices)
    }
    @Test
    fun group_by_category_keeps_all_items() {
        assertEquals(product.size, product.groupByCategory().values.sumOf { it.size })
    }
    @Test
    fun empty_input_is_safe() {
        val empty = emptyList<Product>()
        assertTrue(empty.search("x").isEmpty())
        assertTrue(empty.applySort(SortOption.RATING).isEmpty())
        assertTrue(empty.groupByCategory().isEmpty())
    }
    @Test
    fun cart_total_is_deterministic() {
        val item = CartItem(product.first(), quantity = 3)
        assertEquals(product.first().priceCents * 3, item.lineTotalCents)
    }

}


