package com.ecom.easybuy.products.service.impl;

import com.ecom.easybuy.products.dto.CategoryDto;
import com.ecom.easybuy.products.entity.Category;
import com.ecom.easybuy.products.entity.Product;
import com.ecom.easybuy.products.exception.ResourceNotFoundException;
import com.ecom.easybuy.products.repository.CategoryRepository;
import com.ecom.easybuy.products.repository.ProductRepository;
import com.ecom.easybuy.products.service.CategoryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository,
                               ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);

        List<Product> products = new ArrayList<>();

        for (UUID productId : categoryDto.getProductIds()){
            Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));
            products.add(product);
        }

        category.setProducts(products);

        for (Product product : products) {
            product.getCategories().add(category);
        }

        category = categoryRepository.save(category);

        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category exCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
//        exCategory.setId(categoryDto.getId());
        exCategory.setTitle(categoryDto.getTitle());
        //exCategory.setProducts(categoryDto.getProducts());
        return mapper.map(categoryRepository.save(exCategory), CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> mapper.map(category, CategoryDto.class))
                .toList();
    }

    @Override
    public List<CategoryDto> getCategoriesByProduct(UUID productId) {
        return categoryRepository.findByProductId(productId).stream()
                .map(category -> mapper.map(category, CategoryDto.class))
                .toList();
    }
}
