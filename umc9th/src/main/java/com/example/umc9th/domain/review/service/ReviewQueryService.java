package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResDTO.GetMyDTO> getMyReviews(Long userId, String storeName, Integer ratingGroup) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (storeName != null) {
            builder.and(review.store.name.eq(storeName));
        }
        if (ratingGroup != null) {
            double lowerBound = ratingGroup;
            double upperBound = Math.min(ratingGroup + 0.9, 5.0);

            builder.and(review.rating.between(lowerBound, upperBound));
        }

        return reviewRepository.findMyReviewsByFilter(userId, builder);
    }
}
