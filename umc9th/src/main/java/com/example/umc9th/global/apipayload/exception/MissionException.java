package com.example.umc9th.global.apipayload.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
