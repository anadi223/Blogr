package com.blogr.services;

import com.blogr.entities.Post;
import com.blogr.payloads.PostDto;
import com.blogr.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, int userId, int categoryId);

    PostDto updatePost(PostDto postDto, int postId);

    void deletePost(int postId);

    PostResponse getAllPost(int pageNumber, int pageSize,String sortBy);

    PostDto getPostById(int postId);

    List<PostDto> getPostByCategory(int categoryId);

    List<PostDto> getPostByUser(int userId);
    //search post
    List<PostDto> searchPost(String keyword);
}
