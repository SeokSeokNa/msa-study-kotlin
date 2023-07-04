package io.seok.orderservice.dto

data class Scheme(
    val type: String,
    val fields: List<Field>,
    val optional: Boolean,
    val name: String
)
