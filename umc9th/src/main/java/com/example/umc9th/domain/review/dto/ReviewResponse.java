package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.ReviewReply;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReviewResponse {
    private Long reviewId;
    private String userName;
    private LocalDateTime createdAt;
    private Double rating;
    private String content;
    private List<ReviewImageDto> reviewImages;
    private ReviewReplyDto reviewReply;

    @Builder
    public ReviewResponse(Long reviewId, String userName, LocalDateTime createdAt, Double rating, String content, List<ReviewImageDto> reviewImages, ReviewReplyDto reviewReply) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.createdAt = createdAt;
        this.rating = rating;
        this.content = content;
        this.reviewImages = reviewImages;
        this.reviewReply = reviewReply;
    }
}
