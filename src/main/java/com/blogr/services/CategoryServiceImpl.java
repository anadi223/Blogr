package com.blogr.services;

import com.blogr.entities.Category;
import com.blogr.exceptions.ResourceNotFoundException;
import com.blogr.payloads.CategoryDto;
import com.blogr.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepo repo;
    private final ModelMapper modelMapper;
    CategoryServiceImpl(CategoryRepo repo,ModelMapper mapper){
        this.repo = repo;
        this.modelMapper = mapper;
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = DtoToCategory(categoryDto);
        Category savedCategory = repo.save(category);
        return CategoryToDto(savedCategory);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Optional<Category> optionalCategory = repo.findById(categoryId);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setTitle(categoryDto.getTitle());
            category.setDescription(category.getDescription());
            Category updatedCategory = repo.save(category);
            return CategoryToDto(updatedCategory);
        }else{
            throw new ResourceNotFoundException("Category","Id not present",categoryId);
        }
    }

    public CategoryDto getCategoryById(int categoryId) {
        Optional<Category> optionalCategory = repo.findById(categoryId);
       if(optionalCategory.isPresent()){
           Category category = optionalCategory.get();
           return CategoryToDto(category);
       }else{
           throw new ResourceNotFoundException("Category","Id not present",categoryId);
       }
    }

    public List<CategoryDto> getAllCategory() {
        List<Category> categories = repo.findAll();
        return categories.stream().map(category -> CategoryToDto(category)).toList();
    }

    public void deleteCategory(int CategoryId) {
        Category category = repo.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id not present",CategoryId));
        repo.delete(category);
    }

    private CategoryDto CategoryToDto(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }
    private Category DtoToCategory(CategoryDto dto){
        return modelMapper.map(dto,Category.class);
    }
}
