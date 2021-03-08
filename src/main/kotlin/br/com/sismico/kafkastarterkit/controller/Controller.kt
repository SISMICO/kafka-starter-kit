package br.com.sismico.kafkastarterkit.controller

import br.com.sismico.kafkastarterkit.message.Kafka
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
        private val kafka: Kafka
) {
    @GetMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    fun getHealth(
            @RequestParam name: String,
            @RequestParam email: String
    ) {
        kafka.sendMessage(name, email)
    }
}