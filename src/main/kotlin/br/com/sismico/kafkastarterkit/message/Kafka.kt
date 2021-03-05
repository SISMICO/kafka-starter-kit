package br.com.sismico.kafkastarterkit.message

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Kafka (
        private val template: KafkaTemplate<String, String>
        ) {
    private val topic = "topic"

    fun sendMessage(message: String) {
        template.send(topic, message)
    }

    @KafkaListener(topics = ["topic"], groupId = "kafka-test")
    fun getMessage(message: String) {
        println(message)
    }
}