package io.seok.orderservice.service

import io.seok.orderservice.dto.OrderDto
import io.seok.orderservice.entity.OrderEntity
import io.seok.orderservice.repository.OrderRepository
import io.seok.orderservice.vo.ResponseOrder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderServiceImpl(

    private val orderRepository: OrderRepository
) : OrderService {
    val logger: Logger = LoggerFactory.getLogger(OrderServiceImpl::class.java)


    override fun createOrder(orderDto: OrderDto): OrderDto {
        val orderEntity = OrderEntity.createOrderEntityFromDto(orderDto)
        orderRepository.save(orderEntity)
        return orderDto;
    }

    override fun getOrderByOrderId(orderId: String): OrderDto {
        val findOrderEntity = orderRepository.findByOrderId(orderId)
        return OrderDto.createOrderDto(findOrderEntity)
    }

    override fun getOrdersByUserId(userId: String): List<ResponseOrder> {
        return orderRepository.findByUserId(userId)
            .map { orderEntity ->  ResponseOrder.createResponseOrder(orderEntity)}
    }
}