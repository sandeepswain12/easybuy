package com.ecom.easybuy.products.controller;

import com.ecom.easybuy.products.dto.ReviewDto;
import com.ecom.easybuy.products.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

//    @PostMapping
//    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
//        return ResponseEntity.ok(reviewService.createReview(reviewDto));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto, @PathVariable Long id) {
//        return ResponseEntity.ok(reviewService.updateReview(reviewDto, id));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
//        reviewService.deleteReview(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
//        return ResponseEntity.ok(reviewService.getReviewById(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ReviewDto>> getAllReviews() {
//        return ResponseEntity.ok(reviewService.getAllReviews());
//    }
}
