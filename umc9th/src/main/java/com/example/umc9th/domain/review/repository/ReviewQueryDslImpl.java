package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewImageDto;
import com.example.umc9th.domain.review.dto.ReviewReplyDto;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.QReviewImage;
import com.example.umc9th.domain.review.entity.QReviewReply;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public List<ReviewResponse> findMyReviewsByFilter(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QReviewImage reviewImage = QReviewImage.reviewImage;
        QReviewReply reviewReply = QReviewReply.reviewReply;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.images, reviewImage).fetchJoin()
                .leftJoin(review.reply, reviewReply).fetchJoin()
                .where(predicate)
                .transform(
                        GroupBy.groupBy(review.id).list(
                                Projections.constructor(
                                        ReviewResponse.class,
                                        review.id,
                                        review.user.name,
                                        review.createdAt,
                                        review.rating,
                                        review.content,
                                        GroupBy.list(
                                                Projections.constructor(
                                                        ReviewImageDto.class,
                                                        reviewImage.id,
                                                        reviewImage.imageUrl
                                                )
                                        ),
                                        Projections.constructor(
                                                ReviewReplyDto.class,
                                                reviewReply.id,
                                                reviewReply.content
                                        )
                                )
                        )
                );
    }
}
