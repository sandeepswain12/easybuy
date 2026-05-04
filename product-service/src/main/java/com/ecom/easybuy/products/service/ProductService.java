package com.ecom.easybuy.products.service;

import com.ecom.easybuy.products.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto, UUID id);
    void deleteProduct(UUID id);
    ProductDto getProductById(UUID id);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByCategory(Long categoryId);
}
