package io.seok.orderservice.controller

import io.seok.orderservice.dto.OrderDto
import io.seok.orderservice.dto.RequestOrder
import io.seok.orderservice.service.OrderService
import io.seok.orderservice.vo.ResponseOrder
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order-service")
class OrderController(
    private val env: Environment,
    private val orderService: OrderService
) {

    @GetMapping("/health_check")
    fun status(): String {
        return "It's Working in Order Service on PORT ${env.getProperty("local.server.port")}" //"local.server.port"는 랜덤포트로 할당된 값을 가져올수있게 하는것
    }

    @PostMapping("/{userId}/orders")
    fun createOrder(@PathVariable("userId") userId: String,
                    @RequestBody orderDetails: RequestOrder): ResponseEntity<ResponseOrder> {

        val orderDto = OrderDto.createOrderDto(orderDetails)
        orderDto.userId = userId
        val createOrder = orderService.createOrder(orderDto)

        val responseUser = ResponseOrder.createResponseOrder(createOrder)

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser)
    }

    @GetMapping("/{userId}/orders")
    fun createOrder(@PathVariable("userId") userId: String): ResponseEntity<List<ResponseOrder>> {
        val ordersByUserId = orderService.getOrdersByUserId(userId)
        return ResponseEntity.status(HttpStatus.OK).body(ordersByUserId)
    }
}