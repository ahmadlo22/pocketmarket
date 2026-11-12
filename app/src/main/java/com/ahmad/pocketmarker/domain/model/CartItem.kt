package com.ahmad.pocketmarker.domain.model

data class CartItem(val product: Product,val quantity: Int){
    init {
        require(quantity >= 1){"quantity must be >= 1"}
    }
    val lineTotalCents : Long get() = product.priceCents * quantity
}

