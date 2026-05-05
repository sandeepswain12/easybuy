package com.ecom.easybuy.products.service.impl;

import com.ecom.easybuy.products.dto.CategoryDto;
import com.ecom.easybuy.products.dto.PagedResponse;
import com.ecom.easybuy.products.dto.ProductDto;
import com.ecom.easybuy.products.dto.ReviewDto;
import com.ecom.easybuy.products.entity.Category;
import com.ecom.easybuy.products.entity.Product;
import com.ecom.easybuy.products.exception.ResourceNotFoundException;
import com.ecom.easybuy.products.repository.CategoryRepository;
import com.ecom.easybuy.products.repository.ProductRepository;
import com.ecom.easybuy.products.service.ProductService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Override
    public PagedResponse<ProductDto> getAllProducts(int page, int size) {
        return null;
    }

    @Override
    public ProductDto getProductById(UUID productId) {
        return null;
    }

    @Override
    public PagedResponse<ProductDto> getProductsByCategoryId(Long categoryId, int page, int size) {
        return null;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto updateProduct(UUID productId, ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(UUID productId) {

    }

    @Override
    public ProductDto addCategoryToProduct(UUID productId, Long categoryId) {
        return null;
    }

    @Override
    public ProductDto removeCategoryFromProduct(UUID productId, Long categoryId) {
        return null;
    }

    @Override
    public ReviewDto addReviewToProduct(UUID productId, ReviewDto reviewDto) {
        return null;
    }

    @Override
    public ProductDto addProductImages(UUID productId, List<MultipartFile> files) {
        return null;
    }

    @Override
    public List<String> getProductImages(UUID productId) {
        return List.of();
    }

//    private final ProductRepository productRepository;
//    private final CategoryRepository categoryRepository;
//    private final ModelMapper mapper;
//
//    public ProductServiceImpl(ProductRepository productRepository,
//                              CategoryRepository categoryRepository,
//                              ModelMapper mapper) {
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public ProductDto createProduct(ProductDto productDto) {
//        Product product = mapper.map(productDto, Product.class);
//        return mapper.map(productRepository.save(product), ProductDto.class);
//    }
//
//    @Override
//    public ProductDto createProductWithCategories(ProductDto productDto) {
//        Product product = mapper.map(productDto, Product.class);
//
//        List<Category> categories = new ArrayList<>();
//
//        for(Long categoryId : productDto.getCategoryIds()) {
//            Category category = categoryRepository.findById(categoryId)
//                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
//            categories.add(category);
//        }
//
//        product.setCategories(categories);
//
//        for(Category category : categories) {
//            category.getProducts().add(product);
//        }
//
//        return mapper.map(productRepository.save(product), ProductDto.class);
//    }
//
//
//    @Override
//    public ProductDto updateProduct(ProductDto productDto, UUID id) {
//        Product exproduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//        exproduct.setTitle(productDto.getTitle());
//        exproduct.setShortDesc(productDto.getShortDesc());
//        exproduct.setLongDesc(productDto.getLongDesc());
//        exproduct.setPrice(productDto.getPrice());
//        exproduct.setDiscount(productDto.getDiscount());
//        exproduct.setLive(productDto.getLive());
//        exproduct.setProductImages(productDto.getProductImages());
//        Product updatedProduct = productRepository.save(exproduct);
//        return mapper.map(updatedProduct, ProductDto.class);
//    }
//
//    @Override
//    public void deleteProduct(UUID id) {
//        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//        productRepository.delete(product);
//    }
//
//    @Override
//    public ProductDto getProductById(UUID id) {
//        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//        return mapper.map(product, ProductDto.class);
//    }
//
//    @Override
//    public List<ProductDto> getAllProducts() {
//        return productRepository.findAll().stream()
//                .map(product -> mapper.map(product,ProductDto.class))
//                .toList();
//    }
//
//    @Override
//    public List<ProductDto> getProductsByCategory(Long categoryId) {
//        return productRepository.findByCategoryId(categoryId).stream()
//                .map(product -> mapper.map(product,ProductDto.class))
//                .toList();
//    }
}
