package com.blogr.repositories;

import com.blogr.entities.Category;
import com.blogr.entities.Post;
import com.blogr.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

}
