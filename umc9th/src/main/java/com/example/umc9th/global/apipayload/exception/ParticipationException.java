package com.example.umc9th.global.apipayload.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;

public class ParticipationException extends GeneralException {
    public ParticipationException(BaseErrorCode code) {
        super(code);
    }
}
