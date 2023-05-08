package io.seok.orderservice.entity

import io.seok.orderservice.dto.OrderDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 120, unique = true)
    val productId: String,
    @Column(nullable = false)
    val qty: Int,
    @Column(nullable = false)
    val unitPrice: Int,
    @Column(nullable = false)
    val totalPrice: Int,
    @Column(nullable = false)
    val userId: String,
    @Column(nullable = false, unique = true)
    val orderId: String,


    @Column(nullable = false, updatable = false, insertable = true , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    var createdAt: LocalDateTime

) {

    companion object{
        fun createOrderEntityFromDto(orderDto: OrderDto): OrderEntity {
            return OrderEntity(
                productId = orderDto.productId,
                qty = orderDto.qty,
                unitPrice = orderDto.unitPrice,
                totalPrice = orderDto.totalPrice,
                userId = orderDto.userId,
                orderId = orderDto.orderId,
                createdAt = LocalDateTime.now()
            )
        }
    }
}