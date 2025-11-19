package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apipayload.ApiResponse;
import com.example.umc9th.global.apipayload.code.GeneralSuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewService;

    @GetMapping("/my")
    public ApiResponse<List<ReviewResDTO.GetMyDTO>> getMyReviews(
            @RequestHeader("X-USER-ID") Long memberId, // 임시 헤더로 받기
            @RequestParam(required = false) String storeName,  // 가게 이름 필터
            @RequestParam(required = false) Integer ratingGroup   // 별점 필터
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewService.getMyReviews(memberId, storeName, ratingGroup));
    }
}
