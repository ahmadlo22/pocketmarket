package com.ahmad.pocketmarker.domain.fixtures

import com.ahmad.pocketmarker.domain.model.Category
import com.ahmad.pocketmarker.domain.model.Product
    object ProductFixtures {
        val categories = listOf(
            Category("smartphones", "SmartPhones"),
            Category("laptops", "Laptops"),
            Category("fragrances", "Fragrances"),
            Category("groceries", "Groceries"),
        )

        fun product(count: Int = 30): List<Product> = List(count) { i ->
            val id = i + 1
            Product(
                id = id,
                title = "Product $id",
                description = "Description for product $id",
                category = categories[i % categories.size],
                priceCents = 999L + (i * 250L),
                rating = 3.0 + (i % 20) / 10.0,
                stock = if (i % 7 == 0) 0 else 5 + i,
                thumbnailURL = null,
            )
        }

    }

