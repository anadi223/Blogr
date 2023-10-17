package com.blogr.controllers;

import com.blogr.payloads.ApiResponse;
import com.blogr.payloads.CategoryDto;
import com.blogr.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //POST - create Category
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDTO){
        CategoryDto createdCategoryDto = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    //PUT - update Category
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable int categoryId){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(updatedCategory,HttpStatus.ACCEPTED);
    }

    //DELETE delete Category
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
    }
    //GET Category get
    @GetMapping("/getCategory/{categoryId}")
    public CategoryDto getCategory(@PathVariable int categoryId){
        return categoryService.getCategoryById(categoryId);
    }
    @GetMapping("/getCategory")
    public List<CategoryDto> getAllCategory(){
        return categoryService.getAllCategory();
    }
}
