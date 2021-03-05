package br.com.sismico.kafkastarterkit.controller

import br.com.sismico.kafkastarterkit.message.Kafka
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller (
        private val kafka: Kafka
        ) {
    @GetMapping("/health")
    fun getHealth() : Any {
        kafka.sendMessage("TESTE")
        return mapOf("status" to "OK")
    }
}