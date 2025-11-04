package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.ReviewReply;
import lombok.Getter;

@Getter
public class ReviewReplyDto {

    private Long id;
    private String content;

    public ReviewReplyDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
