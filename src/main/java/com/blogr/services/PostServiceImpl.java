package com.blogr.services;

import com.blogr.entities.Category;
import com.blogr.entities.Post;
import com.blogr.entities.User;
import com.blogr.exceptions.ResourceNotFoundException;
import com.blogr.payloads.PostDto;
import com.blogr.payloads.PostResponse;
import com.blogr.repositories.CategoryRepo;
import com.blogr.repositories.PostRepo;
import com.blogr.repositories.UserRepo;
import com.blogr.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageURL(post.getImageURL());
        Post updatedPost = postRepo.save(post);
        return postToDto(updatedPost);
    }

    public void deletePost(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","post Id",postId));
        postRepo.delete(post);
    }

    public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy) {
        //creating a pageable object to implement pagination
        Pageable p = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));

        Page<Post> pagePosts = postRepo.findAll(p); //JpaRepository find all method takes a pageable object by default
        List<Post> posts = pagePosts.getContent();
        List<PostDto> postDtos = posts.stream().map((post -> postToDto(post))).toList();

        //postResponse class to send custom info about pagination
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
        return postResponse;
    }

    public PostDto getPostById(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Post Id",postId));
        return postToDto(post);
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
