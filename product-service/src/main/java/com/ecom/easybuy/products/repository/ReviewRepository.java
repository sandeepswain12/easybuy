package com.ecom.easybuy.products.repository;

import com.ecom.easybuy.products.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
