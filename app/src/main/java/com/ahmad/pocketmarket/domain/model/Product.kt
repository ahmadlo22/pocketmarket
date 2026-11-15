package com.ahmad.pocketmarket.domain.model

data class Category(val slug:String, val name:String)

data class Product(
    val id : Int,
    val title : String,
    val description : String,
    val category: Category,
    val priceCents : Long,
    val rating : Double,
    val stock : Int,
    val thumbnailURL : String?,
    val imageUrls : List<String> = emptyList(),
    ){
    val inStock : Boolean get() = stock > 0
}


