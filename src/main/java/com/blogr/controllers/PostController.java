package com.blogr.controllers;

import com.blogr.entities.Post;
import com.blogr.payloads.PostDto;
import com.blogr.repositories.PostRepo;
import com.blogr.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController( PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}/createPost")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId){
        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/getPostByUser")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId){
        return new ResponseEntity<>(postService.getPostByUser(userId),HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/getPostByCategory")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId){
        return new ResponseEntity<>(postService.getPostByCategory(categoryId),HttpStatus.OK);
    }
}
