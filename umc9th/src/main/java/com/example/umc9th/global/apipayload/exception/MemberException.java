package com.example.umc9th.global.apipayload.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}
