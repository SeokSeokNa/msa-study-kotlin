package io.seok.orderservice.messagequeue

import com.fasterxml.jackson.core.JsonProcessingException
import io.seok.catalogservice.util.mapperUtil
import io.seok.orderservice.dto.Field
import io.seok.orderservice.dto.OrderDto
import io.seok.orderservice.dto.Payload
import io.seok.orderservice.dto.Scheme
import io.seok.orderservice.dto.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<String,String>
) {

    val log: Logger = LoggerFactory.getLogger(OrderProducer::class.java)


    private final val fields = arrayListOf<Field>(
        Field("string",true,"order_id"),
        Field("string",true,"user_id"),
        Field("string",true,"product_id"),
        Field("int32",true,"qty"),
        Field("int32",true,"unit_price"),
        Field("int32",true,"total_price"),
        )


    val scheme = Scheme(type = "struct",fields = fields,optional = false,name ="orders")

    fun send(topic: String, orderDto: OrderDto): OrderDto {
        val payload = Payload(
            order_id = orderDto.orderId ,
            user_id = orderDto.userId ,
            product_id = orderDto.productId ,
            qty = orderDto.qty ,
            unit_price = orderDto.unitPrice ,
            total_price = orderDto.totalPrice)

        val kafkaOrderDto = KafkaOrderDto(scheme , payload)

        var jsonInString = ""

        try {
            jsonInString = mapperUtil.writeValueAsString(kafkaOrderDto)
        } catch (ex: JsonProcessingException) {
            ex.printStackTrace()
        }

        kafkaTemplate.send(topic, jsonInString)

        log.info("Order Producer sent data from the Order microservice: $kafkaOrderDto")
        return orderDto
    }
}