package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.QMember;
import com.example.umc9th.domain.review.dto.ReviewReplyDto;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.QReviewImage;
import com.example.umc9th.domain.review.entity.QReviewReply;
import com.example.umc9th.domain.store.entity.QStore;
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
    public List<ReviewResDTO.GetMyDTO> findMyReviewsByFilter(Long memberId, Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QReviewImage reviewImage = QReviewImage.reviewImage;
        QReviewReply reviewReply = QReviewReply.reviewReply;
        QMember member = QMember.member;
        QStore store = QStore.store;

        return queryFactory
                .from(review)
                .leftJoin(review.store, store)
                .leftJoin(review.member, member)
                .leftJoin(review.images, reviewImage)
                .leftJoin(review.reply, reviewReply)
                .where(
                        review.member.id.eq(memberId),
                        predicate
                )
                .transform(
                        GroupBy.groupBy(review.id).list(
                                Projections.constructor(
                                        ReviewResDTO.GetMyDTO.class,
                                        review.id,
                                        store.id,
                                        store.name,
                                        member.id,
                                        review.createdAt.stringValue(),
                                        review.rating,
                                        review.content,
                                        GroupBy.list(reviewImage.imageUrl),
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
