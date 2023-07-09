package io.seok.orderservice.controller

import io.seok.orderservice.dto.OrderDto
import io.seok.orderservice.dto.RequestOrder
import io.seok.orderservice.messagequeue.KafkaProducer
import io.seok.orderservice.messagequeue.OrderProducer
import io.seok.orderservice.service.OrderService
import io.seok.orderservice.vo.ResponseOrder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order-service")
class OrderController(
    private val env: Environment,
    private val orderService: OrderService,
    private val kafkaProducer: KafkaProducer,
    private val orderProducer: OrderProducer
) {

    val logger: Logger = LoggerFactory.getLogger(OrderController::class.java)

    @GetMapping("/health_check")
    fun status(): String {
        return "It's Working in Order Service on PORT ${env.getProperty("local.server.port")}" //"local.server.port"는 랜덤포트로 할당된 값을 가져올수있게 하는것
    }

    @PostMapping("/{userId}/orders")
    fun createOrder(@PathVariable("userId") userId: String,
                    @RequestBody orderDetails: RequestOrder): ResponseEntity<ResponseOrder> {

        logger.info("Before add orders data")
        val orderDto = OrderDto.createOrderDto(orderDetails)
        orderDto.userId = userId

        /* 기존 JPA 방식*/
//        val createOrder = orderService.createOrder(orderDto)
//        val responseUser = ResponseOrder.createResponseOrder(createOrder)

        /* send this order to the kafka */
        kafkaProducer.send("example-catalog-topic" , orderDto)
        orderProducer.send("orders" , orderDto)

        val responseOrder = ResponseOrder.createResponseOrder(orderDto)

        logger.info("After added orders data")
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder)
    }

    @GetMapping("/{userId}/orders")
    fun getOrders(@PathVariable("userId") userId: String): ResponseEntity<List<ResponseOrder>> {
        logger.info("Before retrieve orders data")
        val ordersByUserId = orderService.getOrdersByUserId(userId)
        logger.info("After retrieved orders data")
        return ResponseEntity.status(HttpStatus.OK).body(ordersByUserId)
    }
}