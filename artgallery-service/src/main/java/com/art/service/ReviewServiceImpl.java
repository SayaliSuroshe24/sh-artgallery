package com.art.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.ReviewDao;
import com.art.dto.ReviewDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.Review;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(review -> mapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addReview(ReviewDto reviewDTO) {
        Review review = mapper.map(reviewDTO, Review.class);
        Review savedReview = reviewRepository.save(review);
        return "New Review added with id " + savedReview.getReviewId();
    }

    @Override
    public String deleteReview(Long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
            return "Review deleted";
        }
        throw new ResourceNotFoundException("Invalid Review ID!");
    }

    @Override
    public ReviewDto getReviewDetails(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Review ID"));
        return mapper.map(review, ReviewDto.class);
    }

    @Override
    public String updateReview(Long reviewId, ReviewDto reviewDTO) {
        if (reviewRepository.existsById(reviewId)) {
            Review review = mapper.map(reviewDTO, Review.class);
            review.setReviewId(reviewId);
            reviewRepository.save(review);
            return "Update success";
        }
        throw new ResourceNotFoundException("Review doesn't exist!");
    }
}