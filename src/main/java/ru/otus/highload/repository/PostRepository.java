package ru.otus.highload.repository;

import ru.otus.highload.domain.Post;

import java.util.List;
import java.util.UUID;

public interface PostRepository {
    UUID createPost(UUID usrId, String text);
    void updatePost(UUID postId, String text);
    Post getPost(UUID postId);
    void deletePost(UUID postId);
    List<Post> feedPosts(UUID usrId, int offset, int limit);
}
