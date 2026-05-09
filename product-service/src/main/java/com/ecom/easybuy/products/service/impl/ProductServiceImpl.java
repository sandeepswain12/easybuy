package com.ecom.easybuy.products.service.impl;

import com.ecom.easybuy.products.dto.CategoryDto;
import com.ecom.easybuy.products.dto.PagedResponse;
import com.ecom.easybuy.products.dto.ProductDto;
import com.ecom.easybuy.products.dto.ReviewDto;
import com.ecom.easybuy.products.entity.Category;
import com.ecom.easybuy.products.entity.Product;
import com.ecom.easybuy.products.entity.Review;
import com.ecom.easybuy.products.exception.InvalidRequestException;
import com.ecom.easybuy.products.repository.CategoryRepository;
import com.ecom.easybuy.products.repository.ProductRepository;
import com.ecom.easybuy.products.repository.ReviewRepository;
import com.ecom.easybuy.products.service.ImageStorageService;
import com.ecom.easybuy.products.service.ProductService;
import com.ecom.easybuy.products.utils.ProductServiceUtility;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final ProductServiceUtility utility;
    private final ImageStorageService imageStorageService;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ReviewRepository reviewRepository,
                              ImageStorageService imageStorageService,
                              ProductServiceUtility utility) {
        this.utility = utility;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.reviewRepository = reviewRepository;
        this.imageStorageService = imageStorageService;
    }


    @Override
    public PagedResponse<ProductDto> getAllProducts(int page, int size, String sortBy, String sortDir) {
        if (page < 0) throw new IllegalArgumentException("Page index must not be less than zero");
        if (size < 1) throw new IllegalArgumentException("Page size must not be less than one");
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        return utility.toPagedResponse(products,ProductDto.class);
    }

    @Override
    public ProductDto getProductById(UUID productId) {
        return utility.map(utility.findEntity(productRepository, productId),ProductDto.class);
    }

    @Override
    public PagedResponse<ProductDto> getProductsByCategoryId(Long categoryId, int page, int size) {
        if (page < 0) throw new IllegalArgumentException("Page index must not be less than zero");
        if (size < 1) throw new IllegalArgumentException("Page size must not be less than one");
        if (categoryId == null) throw new IllegalArgumentException("Category ID must not be null");
        Pageable pageable = PageRequest.of(page, size);
        Page product = productRepository.findByCategories_Id(categoryId, pageable);
        return utility.toPagedResponse(product,ProductDto.class);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        applyBasicFields(product, productDto);
        List<Category> categories = resolveCategories(productDto.getCategories());
        product.setCategories(categories);
        Product savedProduct = productRepository.save(product);
        syncCategoryLinks(savedProduct, categories);
        return utility.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(UUID productId, ProductDto productDto) {
        Product product = utility.findEntity(productRepository, productId);
        applyBasicFields(product, productDto);
        if (productDto.getCategories() != null){
            List<Category> categories = resolveCategories(productDto.getCategories());
            product.setCategories(categories);
            Product savedProduct = productRepository.save(product);
            syncCategoryLinks(savedProduct, categories);
            return  utility.map(savedProduct, ProductDto.class);
        }
        return utility.map(productRepository.save(product), ProductDto.class);
    }

    @Override
    public void deleteProduct(UUID productId) {
        Product product = utility.findEntity(productRepository, productId);
        productRepository.delete(product);
    }

    @Override
    public ProductDto addCategoryToProduct(UUID productId, Long categoryId) {
        Category category = utility.findEntity(categoryRepository, categoryId);
        Product product = utility.findEntity(productRepository, productId);
        if (!category.getProducts().contains(product)){
            category.getProducts().add(product);
        }
        if (!product.getCategories().contains(category)){
            product.getCategories().add(category);
        }
        categoryRepository.save(category);
        return utility.map(category, ProductDto.class);
    }

    @Override
    public ProductDto removeCategoryFromProduct(UUID productId, Long categoryId) {
        Category category = utility.findEntity(categoryRepository, categoryId);
        Product product = utility.findEntity(productRepository, productId);
        if (category.getProducts().contains(product)){
            category.getProducts().remove(product);
        }
        if (product.getCategories().contains(category)){
            product.getCategories().remove(category);
        }
        categoryRepository.save(category);
        return null;
    }

    @Override
    public ReviewDto addReviewToProduct(UUID productId, ReviewDto reviewDto) {
        Product product = utility.findEntity(productRepository, productId);
        Review review = new Review();
        review.setTitle(reviewDto.getTitle());
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        review.setProduct(product);
        return utility.map(reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public ProductDto addProductImages(UUID productId, List<MultipartFile> files) {
        Product product = utility.findEntity(productRepository, productId);
        List<String> uploadedUrls = utility.uploadImages(files);
        if (product.getProductImages() == null) {
            product.setProductImages(new ArrayList<>());
        }
        product.setProductImages(uploadedUrls);
        return utility.map(productRepository.save(product), ProductDto.class);
    }

    @Override
    public List<String> getProductImages(UUID productId) {
        Product product = utility.findEntity(productRepository, productId);
        return product.getProductImages() == null ? new ArrayList<>() : new ArrayList<>(product.getProductImages());
    }

    private void applyBasicFields(Product product, ProductDto productDto) {
        //custom logic
        product.setTitle(productDto.getTitle());
        product.setShortDesc(productDto.getShortDesc());
        product.setLongDesc(productDto.getLongDesc());
        product.setPrice(productDto.getPrice());
        product.setDiscount(productDto.getDiscount());
        if (productDto.getLive() != null) {
            product.setLive(productDto.getLive());
        }
        if (productDto.getProductImages() != null) {
            product.setProductImages(new ArrayList<>(productDto.getProductImages()));
        }
    }

    private List<Category> resolveCategories(List<CategoryDto> categoryDtos) {
        if(categoryDtos == null){
            return new ArrayList<>();
        }

        List<Category> categories = new ArrayList<>();
        for (CategoryDto categoryDto : categoryDtos) {
            if (categoryDto.getId() == null) {
                Category category = new Category();
                category.setTitle(categoryDto.getTitle());
                categories.add(categoryRepository.save(category));
            }else {
                categories.add(utility.findEntity(categoryRepository, categoryDto.getId()));
            }
        }
        return categories;
    }

    private void syncCategoryLinks(Product product, List<Category> categories) {
        for (Category category : categories) {
            if (!category.getProducts().contains(product)) {
                category.getProducts().add(product);
            }
            categoryRepository.save(category);
        }
    }
}
