package com.blogr.controllers;

import com.blogr.config.Constants;
import com.blogr.entities.Post;
import com.blogr.payloads.PostDto;
import com.blogr.payloads.PostResponse;
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

    @GetMapping("/getPostById/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
    }

    @GetMapping("/getAllPosts")
    public PostResponse getAllPosts(@RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER,required = false) int pageNumber, @RequestParam(value = "pageSize",defaultValue = Constants.PAGE_SIZE,required = false) int pageSize, @RequestParam(value = "sortBy",defaultValue = Constants.SORT_BY,required = false) String sortBy){
        return postService.getAllPost(pageNumber, pageSize,sortBy);
    }

    @PutMapping("updatePost/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable int postId){
        return new ResponseEntity<>(postService.updatePost(postDto,postId),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable int postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //search by keyword
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
        return new ResponseEntity<>(postService.searchPost(keywords),HttpStatus.OK);
    }

}
