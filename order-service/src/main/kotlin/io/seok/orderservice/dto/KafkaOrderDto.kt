package io.seok.orderservice.dto

data class KafkaOrderDto(
    val schema: Scheme,
    val payload: Payload,
)
