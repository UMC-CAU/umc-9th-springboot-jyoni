package com.example.umc9th.domain.test.dto.response;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {

    @Builder
    @Getter
    public static class Testing {
        private String testing;
    }
}
