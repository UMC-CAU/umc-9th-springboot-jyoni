package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {
    @Builder
    public record GetMyDTO (
            Long reviewId,
            Long storeId,
            String storeName,
            Long reviewerId,
            String createdAt,
            Double rating,
            String content,
            List<String> imageUrls,
            ReviewReplyDto reviewReplyDto
    ) {
        public GetMyDTO(
                Long reviewId,
                Long storeId,
                String storeName,
                Long reviewerId,
                String createdAt,
                Double rating,
                String content,
                List<String> imageUrls,
                ReviewReplyDto reviewReplyDto
        ) {
            this.reviewId = reviewId;
            this.storeId = storeId;
            this.storeName = storeName;
            this.reviewerId = reviewerId;
            this.createdAt = createdAt;
            this.rating = rating;
            this.content = content;
            this.imageUrls = imageUrls;
            this.reviewReplyDto = reviewReplyDto;
        }
    }

    @Builder
    public record AddDTO (
            Long reviewId,
            Long storeId,
            String storeName,
            Long reviewerId,
            String createdAt,
            Double rating,
            String content,
            List<String> imageUrls
    ) {}

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}
}
