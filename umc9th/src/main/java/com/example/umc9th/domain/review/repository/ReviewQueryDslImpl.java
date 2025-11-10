package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.QUser;
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
    public List<ReviewResponse> findMyReviewsByFilter(Long userId, Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QReviewImage reviewImage = QReviewImage.reviewImage;
        QReviewReply reviewReply = QReviewReply.reviewReply;
        QUser user = QUser.user;

        return queryFactory
                .from(review)
                .leftJoin(review.user, user)
                .leftJoin(review.images, reviewImage)
                .leftJoin(review.reply, reviewReply)
                .where(
                        review.user.id.eq(userId),
                        predicate
                )
                .transform(
                        GroupBy.groupBy(review.id).list(
                                Projections.constructor(
                                        ReviewResponse.class,
                                        review.id,
                                        user.name,
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
