package com.example.umc9th.domain.test.converter;

import com.example.umc9th.domain.test.dto.response.TestResDTO;

public class TestConverter {

    public static TestResDTO.Testing toTestingDTO(String testing) {
        return TestResDTO.Testing.builder()
                .testing(testing)
                .build();
    }
}
