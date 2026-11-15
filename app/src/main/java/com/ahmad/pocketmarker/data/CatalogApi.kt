package com.ahmad.pocketmarker.data

import com.ahmad.pocketmarker.domain.model.Category
import com.ahmad.pocketmarker.domain.model.Product

interface CatalogApi{
    suspend fun getCategories(): List<Category>
    suspend fun getProduct(): List<Product>
    suspend fun getFeatured() : List<Product>
}