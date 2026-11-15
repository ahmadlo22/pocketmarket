package com.ahmad.pocketmarket.data

import com.ahmad.pocketmarket.domain.model.Category
import com.ahmad.pocketmarket.domain.model.Product

interface CatalogApi{
    suspend fun getCategories(): List<Category>
    suspend fun getProduct(): List<Product>
    suspend fun getFeatured() : List<Product>
}