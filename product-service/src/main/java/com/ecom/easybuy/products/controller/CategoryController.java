package com.ecom.easybuy.products.controller;

import com.ecom.easybuy.products.dto.CategoryDto;
import com.ecom.easybuy.products.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByProductId(@PathVariable UUID productId) {
        return ResponseEntity.ok(categoryService.getCategoriesByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
//    @PostMapping
//    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
//        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
//        return ResponseEntity.ok(createdCategory);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
//        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);
//        return ResponseEntity.ok(updatedCategory);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
//        categoryService.deleteCategory(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CategoryDto>> getAllCategories() {
//        List<CategoryDto> categories = categoryService.getAllCategories();
//        return ResponseEntity.ok(categories);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
//        CategoryDto category = categoryService.getCategoryById(id);
//        return ResponseEntity.ok(category);
//    }
//
//    @GetMapping("/product/{productId}")
//    public ResponseEntity<List<CategoryDto>> getCategoriesByProduct(@PathVariable UUID productId) {
//        List<CategoryDto> categories = categoryService.getCategoriesByProduct(productId);
//        return ResponseEntity.ok(categories);
//    }
}
