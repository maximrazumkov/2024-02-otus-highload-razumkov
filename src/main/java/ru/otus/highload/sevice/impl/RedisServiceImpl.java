package ru.otus.highload.sevice.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.otus.highload.domain.Post;
import ru.otus.highload.sevice.RedisService;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    @Value("${cache-duration:10}")
    private int cacheDuration;

    private static final String FRIEND_POSTS_CACHE_KEY_PREFIX = "friends_posts:";

    private final RedisTemplate<String, List<Post>> redisTemplate;

    @Override
    public void invalidateCache(UUID usrId) {
        redisTemplate.delete(FRIEND_POSTS_CACHE_KEY_PREFIX + usrId);
    }

    @Override
    public List<Post> getPostCache(UUID usrId) {
        return redisTemplate.opsForValue().get(FRIEND_POSTS_CACHE_KEY_PREFIX + usrId);

    }

    @Override
    public void setCache(UUID usrId, List<Post> posts) {
        redisTemplate.opsForValue().set(FRIEND_POSTS_CACHE_KEY_PREFIX + usrId, posts, Duration.ofMinutes(cacheDuration));
    }
}
