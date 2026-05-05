package com.ecom.easybuy.products.service.impl;

import com.ecom.easybuy.products.dto.ReviewDto;
import com.ecom.easybuy.products.entity.Product;
import com.ecom.easybuy.products.entity.Review;
import com.ecom.easybuy.products.exception.ResourceNotFoundException;
import com.ecom.easybuy.products.repository.ProductRepository;
import com.ecom.easybuy.products.repository.ReviewRepository;
import com.ecom.easybuy.products.service.ReviewService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    @Override
    public List<ReviewDto> getAllReviews() {
        return List.of();
    }

    @Override
    public ReviewDto getReviewById(Long reviewId) {
        return null;
    }

    @Override
    public List<ReviewDto> getReviewsByProductId(UUID productId) {
        return List.of();
    }

    @Override
    public ReviewDto createReview(UUID productId, ReviewDto reviewDto) {
        return null;
    }

    @Override
    public ReviewDto updateReview(Long reviewId, ReviewDto reviewDto) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {

    }

//    private final ReviewRepository reviewRepository;
//    private final ProductRepository productRepository;
//    private final ModelMapper mapper;
//
//    public ReviewServiceImpl(
//            ReviewRepository reviewRepository,
//            ProductRepository productRepository,
//            ModelMapper mapper) {
//        this.reviewRepository = reviewRepository;
//        this.productRepository = productRepository;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public ReviewDto createReview(ReviewDto reviewDto) {
//        Review review = mapper.map(reviewDto, Review.class);
//        UUID productId = reviewDto.getProductId();
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//        review.setProduct(product);
//        return mapper.map(reviewRepository.save(review), ReviewDto.class);
//    }
//
//    @Override
//    public ReviewDto updateReview(ReviewDto reviewDto, Long id) {
//        Review exReview = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
//        exReview.setTitle(reviewDto.getTitle());
//        exReview.setComment(reviewDto.getComment());
//        exReview.setRating(reviewDto.getRating());
//        return mapper.map(reviewRepository.save(exReview), ReviewDto.class);
//    }
//
//    @Override
//    public void deleteReview(Long id) {
//        Review exReview = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
//        reviewRepository.delete(exReview);
//    }
//
//    @Override
//    public ReviewDto getReviewById(Long id) {
//        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
//        return mapper.map(review, ReviewDto.class);
//    }
//
//    @Override
//    public List<ReviewDto> getAllReviews() {
//        return reviewRepository.findAll().stream()
//                .map(review -> mapper.map(review, ReviewDto.class))
//                .toList();
//    }
}
