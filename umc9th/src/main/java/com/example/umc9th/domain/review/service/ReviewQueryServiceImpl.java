package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apipayload.exception.StoreException;
import com.example.umc9th.global.apipayload.exception.code.StoreErrorCode;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public List<ReviewResDTO.GetMyDTO> getReviewsByDetailFilter(Long userId, String storeName, Integer ratingGroup) {
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

    @Override
    public ReviewResDTO.AddDTO addReview(Long memberId, ReviewReqDTO.AddDTO dto, Long storeId) {
        // 사용자 조회
        Member member = memberRepository.findMemberById(memberId);

        // 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(dto, member, store);
        reviewRepository.save(review);

        return ReviewConverter.toAddDTO(review);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page) {
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long userId, Integer page) {
        Member member = memberRepository.findMemberById(userId);

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
