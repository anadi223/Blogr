package com.blogr.services.impl;

import com.blogr.entities.Comments;
import com.blogr.entities.Post;
import com.blogr.exceptions.ResourceNotFoundException;
import com.blogr.payloads.CommentDto;
import com.blogr.repositories.CommentRepo;
import com.blogr.repositories.PostRepo;
import com.blogr.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;
    public CommentServiceImpl(PostRepo postRepo, CommentRepo commentRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","with Id",postId));
        Comments comment = modelMapper.map(commentDto, Comments.class);
        comment.setPost(post);
        comment.setContent(comment.getContent());
        Comments savedComment = commentRepo.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(int commentId) {
        Comments comments = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment Id",commentId));
        commentRepo.delete(comments);
    }
}
