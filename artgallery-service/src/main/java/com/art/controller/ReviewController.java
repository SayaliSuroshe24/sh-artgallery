package com.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.art.dto.ReviewDto;
import com.art.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody ReviewDto reviewDTO) {
        return ResponseEntity.ok(reviewService.addReview(reviewDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewDetails(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.deleteReview(id));
    }
}