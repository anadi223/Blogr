package com.blogr.services;

import com.blogr.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,int postId);
    void deleteComment(int commentId);
}
