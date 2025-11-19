package com.example.umc9th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewImageDto {

    private Long id;
    private String imageUrl;

    public ReviewImageDto(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
