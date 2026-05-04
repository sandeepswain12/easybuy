package com.ecom.easybuy.products.service;

import com.ecom.easybuy.products.dto.CategoryDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto createCategoryWithProducts(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Long id);
    void deleteCategory(Long id);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
    List<CategoryDto> getCategoriesByProduct(UUID productId);
}
