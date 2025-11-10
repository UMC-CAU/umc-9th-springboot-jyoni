package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewService;

    public ReviewController(ReviewQueryService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReviewResponse>> getMyReviews(
            @RequestHeader("X-USER-ID") Long userId, // 임시 헤더로 받기
            @RequestParam(required = false) String storeName,  // 가게 이름 필터
            @RequestParam(required = false) Integer ratingGroup   // 별점 필터
    ) {
        List<ReviewResponse> reviews = reviewService.getMyReviews(userId, storeName, ratingGroup);
        return ResponseEntity.ok(reviews);
    }
}
