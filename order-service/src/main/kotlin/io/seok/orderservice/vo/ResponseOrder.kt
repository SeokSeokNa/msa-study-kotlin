package io.seok.orderservice.vo

import com.fasterxml.jackson.annotation.JsonInclude
import io.seok.orderservice.dto.OrderDto
import io.seok.orderservice.entity.OrderEntity
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL) //null 필드는 반환하지 않음
data class ResponseOrder(
    val productId: String,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val orderId: String
) {

    companion object {
        fun createResponseOrder(orderEntity: OrderEntity): ResponseOrder{
            return ResponseOrder(
                productId = orderEntity.productId,
                qty = orderEntity.qty,
                unitPrice = orderEntity.unitPrice,
                totalPrice = orderEntity.totalPrice,
                orderId = orderEntity.orderId
            )
        }

        fun createResponseOrder(orderDto: OrderDto): ResponseOrder {
            return ResponseOrder(
                productId = orderDto.productId,
                qty = orderDto.qty,
                unitPrice = orderDto.unitPrice,
                totalPrice = orderDto.totalPrice,
                orderId = orderDto.orderId
            )
        }
    }
}
