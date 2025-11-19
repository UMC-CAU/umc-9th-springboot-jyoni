package com.example.umc9th.domain.review.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReviewReqDTO {

    public record AddDTO (
            @NotNull
            Double rating,
            @NotNull
            String content,
            @NotNull
            Long storeId,
            List<String> reviewImageUrls
    ){}
}
