package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;

import java.util.List;

public interface ReviewQueryService {

    List<ReviewResDTO.GetMyDTO> getMyReviews(Long userId, String storeName, Integer ratingGroup);
    ReviewResDTO.AddDTO addReview(Long memberId, ReviewReqDTO.AddDTO dto, Long storeId);
    // 검색 API
//    List<Review> searchReview(String filter, String type) throws Exception;
    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);
}
