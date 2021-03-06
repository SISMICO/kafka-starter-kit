package br.com.sismico.kafkastarterkit.message

import br.com.sismico.kafkastarterkit.entity.User
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Kafka (
        private val template: KafkaTemplate<String, String>
        ) {
    private val topic = "topic"

    fun sendMessage(message: String) {
        val jsonMapper = ObjectMapper().apply {
            registerKotlinModule()
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            setDateFormat(StdDateFormat())
        }
        template.send(topic, jsonMapper.writeValueAsString(User(nane = message)))
    }

    @KafkaListener(topics = ["topic"], groupId = "kafka-test")
    fun getMessage(message: String) {
        val jsonMapper = ObjectMapper().apply {
            registerKotlinModule()
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            setDateFormat(StdDateFormat())
        }
        val user = jsonMapper.readValue(message, User::class.java)

        println(user.nane)
    }
}