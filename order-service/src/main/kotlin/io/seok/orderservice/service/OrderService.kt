package io.seok.orderservice.service

import io.seok.orderservice.dto.OrderDto
import io.seok.orderservice.vo.ResponseOrder

interface OrderService {

    //주문 생성
    fun createOrder(orderDetails: OrderDto): OrderDto
    //주문 1건 조회
    fun getOrderByOrderId(orderId: String): OrderDto
    //유저의 모든 주문 조회
    fun getOrdersByUserId(userId: String): List<ResponseOrder>
}