package com.art.service;

import java.util.List;

import com.art.dto.CategoryDto;

public interface CategoryService {
	  List<CategoryDto> getAllCategories();
	    String addCategory(CategoryDto categoryDto);
	    String deleteCategory(Long categoryId);
	    CategoryDto getCategoryDetails(Long categoryId);
	    String updateCategory(Long categoryId, CategoryDto categoryDto);
}
