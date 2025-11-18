package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    List<ReviewResponse> findMyReviewsByFilter(Long memberId, Predicate predicate);
}
