package ru.otus.highload.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.highload.domain.Post;
import ru.otus.highload.dto.PostDTO;
import ru.otus.highload.sevice.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postCreateDTO) {
        Post post = new Post();
        post.setUsrId(postCreateDTO.getUserId());
        post.setText(postCreateDTO.getPost());
        Post postId = postService.createPost(post);
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(postId.getId());
        postDTO.setCreatedAt(post.getCreatedAt());
        return ResponseEntity.ok(postDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updatePost(@RequestBody PostDTO postDTO) {
        Post post = new Post();
        post.setUsrId(postDTO.getUserId());
        post.setId(postDTO.getPostId());
        post.setText(postDTO.getPost());
        postService.updatePost(post);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{post_id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{post_id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("post_id") UUID postId) {
        Post post = postService.getPostById(postId);
        PostDTO postDTO = new PostDTO();
        postDTO.setPost(post.getText());
        postDTO.setUserId(post.getUsrId());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());
        return ResponseEntity.ok(postDTO);
    }

    @GetMapping("/feed/{user_id}")
    public ResponseEntity<List<PostDTO>> getFeed(@PathVariable("user_id") UUID userId) {
        List<Post> feed = postService.getFeed(userId);
        List<PostDTO> postDTOFeed = new ArrayList<>();
        for (Post post : feed) {
            PostDTO postDTO = new PostDTO();
            postDTO.setPostId(post.getId());
            postDTO.setUserId(post.getUsrId());
            postDTO.setPost(post.getText());
            postDTO.setCreatedAt(post.getCreatedAt());
            postDTO.setUpdatedAt(post.getUpdatedAt());
            postDTOFeed.add(postDTO);
        }
        return ResponseEntity.ok(postDTOFeed);
    }
}
