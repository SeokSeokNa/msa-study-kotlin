package io.seok.orderservice.dto

import io.seok.orderservice.entity.OrderEntity
import java.util.UUID

data class OrderDto(
     var productId: String,
     var qty: Int,
     var unitPrice: Int,
     var totalPrice: Int,

     var orderId: String,
     var userId: String
) {
     companion object {
          fun createOrderDto(orderEntity: OrderEntity): OrderDto {
               return OrderDto(
                    productId = orderEntity.productId,
                    qty = orderEntity.qty,
                    unitPrice = orderEntity.unitPrice,
                    totalPrice = orderEntity.totalPrice,
                    orderId = orderEntity.orderId,
                    userId = orderEntity.userId
               )
          }

          fun createOrderDto(requestOrder: RequestOrder): OrderDto {
               return OrderDto(
                    productId = requestOrder.productId,
                    qty = requestOrder.qty,
                    unitPrice = requestOrder.unitPrice,
                    totalPrice = requestOrder.unitPrice * requestOrder.qty,
                    orderId = UUID.randomUUID().toString(),
                    ""
               )
          }
     }
}
