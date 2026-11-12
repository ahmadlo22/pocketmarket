package com.ahmad.pocketmarker.domain.model

enum class OrderStatus{PLACED, PACKED,SHIPPED,DELIVERED}

data class Order(
    val id : String,
    val items : List<CartItem>,
    val createdAtMillis: Long,
    val status : OrderStatus = OrderStatus.PLACED,
){
    val totalCents : Long get() = items.sumOf { it.lineTotalCents }
}
