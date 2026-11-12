package com.ahmad.pocketmarker.domain.model

enum class SortOption{PRICE_ASC,PRICE_DEC,RATING,TITLE}

fun List<Product>.filterByCategory(slug : String?) : List<Product> =
    if (slug == null) this else filter { it.category.slug == slug }

fun List<Product>.search(query : String) : List<Product> {
    val q = query.trim()
    return if (q.isEmpty()) this else filter { it.title.contains(q,ignoreCase = true) }
}
fun List<Product>.applySort(option: SortOption) : List<Product> = when(option){
    SortOption.PRICE_ASC -> sortedBy { it.priceCents }
    SortOption.PRICE_DEC -> sortedByDescending { it.priceCents }
    SortOption.RATING -> sortedByDescending { it.rating }
    SortOption.TITLE -> sortedBy { it.title.lowercase() }
}
fun List<Product>.groupByCategory(): Map<Category, List<Product>> = groupBy { it.category }