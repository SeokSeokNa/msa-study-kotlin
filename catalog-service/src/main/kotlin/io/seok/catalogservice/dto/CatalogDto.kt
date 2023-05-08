package io.seok.catalogservice.dto

data class CatalogDto(
    private var productId: String,
    private var qty: Int,
    private var unitPrice: Int,
    private var totalPrice: Int,

    private var orderId: String,
    private var userId: String
)
