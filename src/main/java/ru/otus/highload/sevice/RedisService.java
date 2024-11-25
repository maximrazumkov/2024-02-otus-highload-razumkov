package ru.otus.highload.sevice;

import ru.otus.highload.domain.Post;

import java.util.List;
import java.util.UUID;

public interface RedisService {
    void invalidateCache(UUID usrId);
    List<Post> getPostCache(UUID usrId);
    void setCache(UUID usrId, List<Post> posts);
}
