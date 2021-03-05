package br.com.sismico.kafkastarterkit

import br.com.sismico.kafkastarterkit.message.AvroSerializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.ByteArraySerializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.core.DefaultKafkaProducerFactory

import org.springframework.kafka.core.ProducerFactory





@EnableKafka
@Configuration
class KafkaConfig {
    private val TRUSTSTORE_JKS = "/var/private/ssl/kafka.client.truststore.jks"
    private val SASL_PROTOCOL = "SASL_PLAINTEXT"
    private val jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";"
    private val prodJaasCfg = String.format(jaasTemplate, "admin", "kafkasecret")


    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = AvroSerializer::class.java
        configProps[ProducerConfig.ACKS_CONFIG] = "all";
        configProps[ProducerConfig.CLIENT_ID_CONFIG] = "cid1";
        configProps["sasl.jaas.config"] = prodJaasCfg;
        configProps["security.protocol"] = SASL_PROTOCOL;
        configProps["sasl.mechanism"] = "PLAIN";
        configProps["ssl.truststore.location"] = TRUSTSTORE_JKS;
        configProps["ssl.truststore.password"] = "password";
        configProps["ssl.endpoint.identification.algorithm"] = "";
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String?, String?>? {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "kafka-test"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = ByteArrayDeserializer::class.java
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest";
        props["sasl.jaas.config"] = prodJaasCfg;
        props["security.protocol"] = SASL_PROTOCOL;
        props["sasl.mechanism"] = "PLAIN";
        props["ssl.truststore.location"] = TRUSTSTORE_JKS;
        props["ssl.truststore.password"] = "password";
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }
}