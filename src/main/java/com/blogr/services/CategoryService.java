package com.blogr.services;

import com.blogr.payloads.CategoryDto;
import com.blogr.payloads.UserDTO;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
    CategoryDto getCategoryById(int CategoryId);
    List<CategoryDto> getAllCategory();
    void deleteCategory(int CategoryId);
}
