package io.seok.orderservice.dto

data class RequestOrder(
    val productId: String,
    val qty: Int,
    val unitPrice: Int
)
