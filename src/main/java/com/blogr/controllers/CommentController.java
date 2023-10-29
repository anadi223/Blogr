package com.blogr.controllers;

import com.blogr.payloads.ApiResponse;
import com.blogr.payloads.CommentDto;
import com.blogr.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/createComment/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId){
        return new ResponseEntity<>(commentService.createComment(commentDto,postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ApiResponse deleteComment(@PathVariable int commentId){
        commentService.deleteComment(commentId);
        return new ApiResponse("Comment Deleted Successfully",true);
    }
}
