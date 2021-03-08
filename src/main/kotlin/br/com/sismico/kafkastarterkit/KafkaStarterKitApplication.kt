package br.com.sismico.kafkastarterkit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class KafkaStarterKitApplication

fun main(args: Array<String>) {
    runApplication<KafkaStarterKitApplication>(*args)
}
