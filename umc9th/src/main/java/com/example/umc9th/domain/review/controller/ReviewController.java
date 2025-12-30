package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apipayload.ApiResponse;
import com.example.umc9th.global.apipayload.exception.code.ReviewSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryService reviewService;

    @GetMapping("/reviews/detail")
    public ApiResponse<List<ReviewResDTO.GetMyDTO>> getReviewsByDetailFilter(
            @RequestHeader("X-USER-ID") Long memberId, // 임시 헤더로 받기
            @RequestParam(required = false) String storeName,  // 가게 이름 필터
            @RequestParam(required = false) Integer ratingGroup   // 별점 필터
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewService.getReviewsByDetailFilter(memberId, storeName, ratingGroup));
    }

    @PostMapping("/store/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.AddDTO> addReview(
            @RequestHeader("X-USER-ID") Long memberId, // 임시 헤더로 받기
            @RequestBody @Valid ReviewReqDTO.AddDTO dto,
            @PathVariable Long storeId
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewService.addReview(memberId, dto, storeId));
    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewService.findReview(storeName, page));
    }

    // 내가 작성한 리뷰 목록 조회
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API",
            description = "내가 작성한 리뷰의 목록을 조회합니다. 페이지네이션으로 제공합니다."
    )
    @GetMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestHeader("X-USER-ID") Long memberId, // 임시 헤더로 받기
            @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewService.getMyReviews(memberId, page));
    }
}
