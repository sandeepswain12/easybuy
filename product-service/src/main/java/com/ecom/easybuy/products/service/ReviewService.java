package com.ecom.easybuy.products.service;


import com.ecom.easybuy.products.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto updateReview(ReviewDto reviewDto, Long id);
    void deleteReview(Long id);
    ReviewDto getReviewById(Long id);
    List<ReviewDto> getAllReviews();
}
