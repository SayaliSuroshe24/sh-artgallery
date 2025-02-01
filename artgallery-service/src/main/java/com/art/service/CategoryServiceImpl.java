package com.art.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.CategoryDao;
import com.art.dto.CategoryDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.Category;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> mapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addCategory(CategoryDto categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return "New Category added with id " + savedCategory.getCategoryId();
    }

    @Override
    public String deleteCategory(Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return "Category deleted";
        }
        throw new ResourceNotFoundException("Invalid Category ID!");
    }

    @Override
    public CategoryDto getCategoryDetails(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Category ID"));
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public String updateCategory(Long categoryId, CategoryDto categoryDTO) {
        if (categoryRepository.existsById(categoryId)) {
            Category category = mapper.map(categoryDTO, Category.class);
            category.setCategoryId(categoryId);
            categoryRepository.save(category);
            return "Update success";
        }
        throw new ResourceNotFoundException("Category doesn't exist!");
    }
}