package io.seok.catalogservice.messagequeue

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@EnableKafka
@Configuration
class KafkaConsumerConfig {


    //kafka에 접속 할 수있는 정보를 담은 Bean
    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val properties = hashMapOf<String, Any>(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "127.0.0.1:9092",
            ConsumerConfig.GROUP_ID_CONFIG to "consumerGroupId", //group id는 topic에 쌓여있는 메세지를 가져갈 수 있는 그룹을 말한다.(특정 consumer 들만 데이터를 수신할 수 있도록 하기위해 그룹 Id를 만든다)
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            )

        return DefaultKafkaConsumerFactory(properties)
    }

    //Listener
    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String,String> {
        val kafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String,String>()
        kafkaListenerContainerFactory.consumerFactory = consumerFactory()

        return kafkaListenerContainerFactory
    }

}