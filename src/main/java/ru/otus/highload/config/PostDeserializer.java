package ru.otus.highload.config;

//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.apache.kafka.common.serialization.Deserializer;
//import ru.otus.highload.domain.Post;
//
//import java.util.Map;
//
//public class PostDeserializer implements Deserializer<Post> {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void configure(Map<String, ?> configs, boolean isKey) {
//        // Настройка при необходимости
//    }
//
//    @Override
//    public Post deserialize(String topic, byte[] data) {
//        try {
//            return objectMapper.readValue(data, Post.class);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to deserialize Post", e);
//        }
//    }
//
//    @Override
//    public void close() {
//        // Освобождение ресурсов, если необходимо
//    }
//}
