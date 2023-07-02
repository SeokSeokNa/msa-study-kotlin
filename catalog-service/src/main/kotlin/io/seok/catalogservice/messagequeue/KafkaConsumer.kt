package io.seok.catalogservice.messagequeue

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import io.seok.catalogservice.repository.CatalogRepository
import io.seok.catalogservice.util.mapperUtil
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
@Slf4j
class KafkaConsumer(
    val catalogRepository: CatalogRepository
) {

    val log: Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics = ["example-catalog-topic"])
    fun updateQty(kafkaMessage: String) {

        log.info("Kafka Message: -> $kafkaMessage")

        var map = hashMapOf<Any, Any>()
        try {
            map = mapperUtil.readValue(kafkaMessage, object : TypeReference<HashMap<Any, Any>>() {})
        } catch (ex: JsonProcessingException) {
            ex.printStackTrace()
        }

        val catalogEntity = catalogRepository.findByProductId(map["productId"] as String)

        if (catalogEntity != null) {
            catalogEntity.stock = catalogEntity.stock - map["qty"] as Int
            catalogRepository.save(catalogEntity)
        }
    }
}