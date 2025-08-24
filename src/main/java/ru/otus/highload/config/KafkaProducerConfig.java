package ru.otus.highload.config;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.Serializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.ProducerFactory;
//import ru.otus.highload.domain.Post;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaProducerConfig {
//
//    @Value("${spring.kafka.producer.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Value("${spring.kafka.producer.key-serializer}")
//    private String keySerializer;
//
//    @Value("${spring.kafka.producer.value-serializer}")
//    private String valueSerializer;
//
//
//    @Bean
//    public ProducerFactory<String, Post> producerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Post> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//}