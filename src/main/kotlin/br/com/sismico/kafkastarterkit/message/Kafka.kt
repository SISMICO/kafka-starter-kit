package br.com.sismico.kafkastarterkit.message

import br.com.sismico.avro.ExampleMessage
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Kafka(
        private val template: KafkaTemplate<String, ExampleMessage>
) {
    private val topic = "topic"

    fun sendMessage(name: String, email: String) {
        val avro = ExampleMessage().apply {
            setUsername(name)
            setEmail(email)
        }
        template.send(topic, avro)
    }

    @KafkaListener(topics = ["topic"], groupId = "kafka-test")
    fun getMessage(message: ConsumerRecord<String, ExampleMessage>) {
        var example = message.value()
        println("Name: ${example.getUsername()}\n EMail: ${example.getEmail()}")
    }
}