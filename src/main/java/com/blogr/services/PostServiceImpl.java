package com.blogr.services;

import com.blogr.entities.Category;
import com.blogr.entities.Post;
import com.blogr.entities.User;
import com.blogr.exceptions.ResourceNotFoundException;
import com.blogr.payloads.PostDto;
import com.blogr.repositories.CategoryRepo;
import com.blogr.repositories.PostRepo;
import com.blogr.repositories.UserRepo;
import com.blogr.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;

    public PostServiceImpl(PostRepo repo, ModelMapper modelMapper, UserRepo userRepo, CategoryRepo categoryRepo) {
        this.postRepo = repo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }


    public PostDto createPost(PostDto postDto, int userId, int categoryId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","User Id",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","category Id",categoryId));
        Post post = dtoToPost(postDto);
        post.setImageURL("default.png");
        post.setPostAdded(new Date());
        post.setCategory(category);
        post.setUser(user);

        Post updatedPost = postRepo.save(post);
        return postToDto(updatedPost);
    }

    public PostDto updatePost(PostDto postDto, int postId) {
        return null;
    }

    public void deletePost(int postId) {

    }

    public List<PostDto> getAllPost() {
        return null;
    }

    public PostDto getPostById(int postId) {
        return null;
    }

    public List<PostDto> getPostByCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> posts = postRepo.findByCategory(category);
        return posts.stream().map((post -> postToDto(post))).toList();
    }

    public List<PostDto> getPostByUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","user Id",userId));
        List<Post> posts = postRepo.findByUser(user);
        return posts.stream().map((post -> postToDto(post))).toList();
    }

    public List<PostDto> searchPost(String keyword) {

        return null;
    }

    private PostDto postToDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }
    private Post dtoToPost(PostDto dto){
        return modelMapper.map(dto,Post.class);
    }
}
