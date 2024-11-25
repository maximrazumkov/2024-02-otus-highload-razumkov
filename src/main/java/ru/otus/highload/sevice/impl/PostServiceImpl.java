package ru.otus.highload.sevice.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.otus.highload.domain.Post;
import ru.otus.highload.domain.User;
import ru.otus.highload.repository.PostRepository;
import ru.otus.highload.repository.UserRepository;
import ru.otus.highload.sevice.PostService;
import ru.otus.highload.sevice.RedisService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Value("${topic.name}")
    private String topicName;

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final RedisService redisService;
    private final KafkaTemplate<String, Post> kafkaTemplate;

    @Override
    @Transactional
    public Post createPost(Post post) {
        UUID postId = postRepository.createPost(post.getUsrId(), post.getText());
        sendToKafka(post.getUsrId(), postId);
        Post newPost = new Post();
        newPost.setId(postId);
        return newPost;
    }

    @Override
    @Transactional
    public void updatePost(Post post) {
        postRepository.updatePost(post.getId(), post.getText());
        Post newPost = postRepository.getPost(post.getId());
        sendToKafka(newPost.getUsrId(), newPost.getId());
    }

    @Override
    @Transactional
    public void deletePost(UUID postId) {
        postRepository.deletePost(postId);
        Post newPost = postRepository.getPost(postId);
        sendToKafka(newPost.getUsrId(), newPost.getId());
    }

    @Override
    public Post getPostById(UUID postId) {
        return postRepository.getPost(postId);
    }

    @Override
    public List<Post> getFeed(UUID userId) {
        List<Post> cachedPosts = redisService.getPostCache(userId);
        if (CollectionUtils.isEmpty(cachedPosts)) {
            cachedPosts = postRepository.feedPosts(userId, 0, 1000);
            redisService.setCache(userId, cachedPosts);
            return cachedPosts;
        }
        return cachedPosts;
    }

    private void sendToKafka(UUID userId, UUID postId) {
        User user = userRepository.getUserById(userId);
        if (!user.getIsCelebrity()) {
            Post kafkaPost = new Post();
            kafkaPost.setId(postId);
            kafkaPost.setUsrId(userId);
            kafkaTemplate.send(topicName, kafkaPost);
        }
    }
}
