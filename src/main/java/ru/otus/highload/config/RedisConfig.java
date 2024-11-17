package ru.otus.highload.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.core.RedisTemplate;
import ru.otus.highload.domain.Post;

import java.util.List;

@Configuration
public class RedisConfig {

    private final ObjectMapper objectMapper;

    public RedisConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(
            @Value("${spring.redis.host}") String host,
            @Value("${spring.redis.port}") Integer port
    ) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, List<Post>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, List<Post>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // Настраиваем сериализацию ключей и значений
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new RedisSerializer<List<Post>>() {
            @Override
            public byte[] serialize(List<Post> posts) throws SerializationException {
                try {
                    return objectMapper.writeValueAsBytes(posts);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to serialize", e);
                }
            }

            @Override
            public List<Post> deserialize(byte[] bytes) {
                if (bytes == null || bytes.length == 0) return null;
                try {
                    CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Post.class);
                    return objectMapper.readValue(bytes, type);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to deserialize", e);
                }
            }
        });

        return redisTemplate;
    }
}
