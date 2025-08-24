package ru.otus.highload.sevice.impl;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//import ru.otus.highload.domain.Post;
//import ru.otus.highload.domain.User;
//import ru.otus.highload.repository.FriendRepository;
//import ru.otus.highload.repository.PostRepository;
//import ru.otus.highload.repository.UserRepository;
//import ru.otus.highload.sevice.RedisService;
//
//import java.util.List;
//import java.util.UUID;
//
//import static java.util.Objects.isNull;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class PostConsumerServiceImpl {
//
//    private final PostRepository postRepository;
//    private final UserRepository userRepository;
//    private final FriendRepository friendRepository;
//    private final RedisService redisService;
//
//    @Transactional
//    @KafkaListener(topics = "${topic.name}", groupId = "post-consumer-group", containerFactory = "kafkaListenerContainerFactory")
//    public void consumePostMessage(Post post, Acknowledgment acknowledgment) {
//        log.info("Consuming post message: {}", post);
//
//        Post postDb = postRepository.getPost(post.getId());
//        if (isNull(postDb)) {
//            log.info("Post with id {} does not exist. Skipping.", post.getId());
//            acknowledgment.acknowledge();
//            return;
//        }
//
//        User user = userRepository.getUserById(post.getUsrId());
//        if (isNull(user)) {
//            log.info("User {} is popular, skipping message processing.", post.getUsrId());
//            acknowledgment.acknowledge();
//            return;
//        }
//
//        if (user.getIsCelebrity()) {
//            log.info("User {} is celebrity, skipping message processing.", post.getUsrId());
//            acknowledgment.acknowledge();
//            return;
//        }
//
//        List<UUID> usrUuidList = friendRepository.findSubscribeIdListByUsrId(post.getUsrId());
//        usrUuidList.forEach(userId -> {
//            redisService.invalidateCache(userId);
//            List<Post> posts = postRepository.feedPosts(userId, 0, 1000);
//            if (!CollectionUtils.isEmpty(posts)) {
//                redisService.setCache(userId, posts);
//            }
//        });
//
//        acknowledgment.acknowledge();
//    }
//}