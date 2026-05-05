package com.ecom.easybuy.products.controller;

import com.ecom.easybuy.products.dto.ProductDto;
import com.ecom.easybuy.products.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @PostMapping
//    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
//        ProductDto createdProduct = productService.createProduct(productDto);
//        return ResponseEntity.ok(createdProduct);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable UUID id) {
//        ProductDto updatedProduct = productService.updateProduct(productDto, id);
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ProductDto>> getAllProducts() {
//        List<ProductDto> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
//        ProductDto product = productService.getProductById(id);
//        return ResponseEntity.ok(product);
//    }
//
//    @GetMapping("/category/{categoryId}")
//    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId) {
//        List<ProductDto> products = productService.getProductsByCategory(categoryId);
//        return ResponseEntity.ok(products);
//    }
}
