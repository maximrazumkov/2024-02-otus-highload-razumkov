package ru.otus.highload.config;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.config.TopicBuilder;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import ru.otus.highload.domain.Post;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaListenerConfig {
//
//    @Value("${spring.kafka.consumer.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Value("${spring.kafka.consumer.group-id}")
//    private String groupId;
//
//    @Value("${spring.kafka.consumer.enable-auto-commit}")
//    private boolean enableAutoCommit;
//
//    @Value("${spring.kafka.consumer.key-deserializer}")
//    private String keyDeserializer;
//
//    @Value("${spring.kafka.consumer.value-deserializer}")
//    private String valueDeserializer;
//
//    @Value("${spring.kafka.consumer.auto-offset-reset}")
//    private String autoOffsetReset;
//
//    @Value("${topic.name}")
//    private String topicName;
//
//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder
//                .name(topicName)
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Post>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper) {
//        ConcurrentKafkaListenerContainerFactory<String, Post> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory(kafkaProperties, objectMapper));
//        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
//        factory.getContainerProperties().setAckMode(kafkaProperties.getListener().getAckMode());
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, Post> consumerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper) {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//}
