package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewImage;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.AddDTO dto, Member member, Store store) {
        Review review = Review.builder()
                .member(member)
                .rating(dto.rating())
                .content(dto.content())
                .store(store)
                .build();

        // 이미지가 있다면 ReviewImage 변환
        if (dto.reviewImageUrls() != null && !dto.reviewImageUrls().isEmpty()) {
            for (String url : dto.reviewImageUrls()) {
                ReviewImage reviewImage = ReviewImage.builder()
                        .imageUrl(url)
                        .review(review)
                        .build();

                review.getImages().add(reviewImage);
            }
        }

        return review;
    }

    public static ReviewResDTO.AddDTO toAddDTO(Review review) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return ReviewResDTO.AddDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .reviewerId(review.getMember().getId())
                .createdAt(review.getCreatedAt().format(formatter))
                .rating(review.getRating())
                .content(review.getContent())
                .imageUrls(review.getImages().stream()
                        .map(image -> image.getImageUrl())
                        .collect(Collectors.toList()))
                .build();
    }

    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ) {
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(Review review) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating().floatValue())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
