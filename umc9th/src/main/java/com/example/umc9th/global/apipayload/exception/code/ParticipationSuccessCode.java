package com.example.umc9th.global.apipayload.exception.code;

import com.example.umc9th.global.apipayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ParticipationSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.FOUND,
            "PARTICIATE200_1",
            "미션 참여를 성공적으로 조회했습니다."),
    ;

    private HttpStatus status;
    private String code;
    private String message;
}
