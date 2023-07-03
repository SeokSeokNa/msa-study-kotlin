package io.seok.orderservice.messagequeue

import com.fasterxml.jackson.core.JsonProcessingException
import io.seok.catalogservice.util.mapperUtil
import io.seok.orderservice.dto.OrderDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String,String>
) {

    val log: Logger = LoggerFactory.getLogger(KafkaProducer::class.java)

    fun send(topic: String, orderDto: OrderDto): OrderDto {
        var jsonInString = ""

        try {
            jsonInString = mapperUtil.writeValueAsString(orderDto)
        } catch (ex: JsonProcessingException) {
            ex.printStackTrace()
        }

        kafkaTemplate.send(topic, jsonInString)
        log.info("Kafka Producer sent data from the Order microservice: $orderDto")


        return orderDto
    }
}