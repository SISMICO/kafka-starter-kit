package br.com.sismico.kafkastarterkit

import org.springframework.context.annotation.Configuration


@Configuration
class KafkaConfig {
//    @Bean
//    fun consumerFactory(
//            @Value("\${spring.kafka.consumer.group-id}") group: String = "",
//            @Value("\${spring.kafka.consumer.key-deserializer}") key: String = "",
//            @Value("\${spring.kafka.consumer.value-deserializer}") value: String = "",
//            @Value("\${spring.kafka.consumer.auto-offset-reset}") offset: String = "",
//    ): ConsumerFactory<String?, String?>? {
//        val props: Map<String, String> = mapOf(
//                ConsumerConfig.GROUP_ID_CONFIG to group,
//                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to key,
//                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to value,
//                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to offset
//        )
//        println(props)
//        return DefaultKafkaConsumerFactory(props)
//    }
//
//    @Bean
//    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String>? {
//        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
//        factory.consumerFactory = consumerFactory()
//        return factory
//    }
}