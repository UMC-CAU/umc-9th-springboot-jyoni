package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.ReviewImage;
import lombok.Getter;

@Getter
public class ReviewImageDto {

    private Long id;
    private String imageUrl;

    public ReviewImageDto(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
