package com.blogr.repositories;

import com.blogr.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
