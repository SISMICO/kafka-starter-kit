package br.com.sismico.kafkastarterkit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaStarterKitApplication

fun main(args: Array<String>) {
    runApplication<KafkaStarterKitApplication>(*args)
}
