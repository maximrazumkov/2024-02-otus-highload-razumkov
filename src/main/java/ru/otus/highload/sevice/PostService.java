package ru.otus.highload.sevice;

import ru.otus.highload.domain.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post createPost(Post post);
    void updatePost(Post post);
    void deletePost(UUID postId);
    Post getPostById(UUID postId);
    List<Post> getFeed(UUID userId);
}
