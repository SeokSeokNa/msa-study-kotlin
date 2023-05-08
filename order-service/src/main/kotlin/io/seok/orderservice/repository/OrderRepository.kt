package io.seok.orderservice.repository

import io.seok.orderservice.entity.OrderEntity
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<OrderEntity , Long> {

    fun findByOrderId(orderId: String): OrderEntity

    fun findByUserId(userId: String): List<OrderEntity>
}