package com.ecom.easybuy.products.repository;

import com.ecom.easybuy.products.entity.Category;
import com.ecom.easybuy.products.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Products, UUID> {

    @Query("SELECT p FROM Products p JOIN p.categories c WHERE c.id = :categoryId")
    List<Products> findByCategoryId(@Param("categoryId") Long categoryId);
}
