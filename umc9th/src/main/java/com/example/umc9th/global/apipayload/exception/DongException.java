package com.example.umc9th.global.apipayload.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;

public class DongException extends GeneralException {
    public DongException(BaseErrorCode code) {
        super(code);
    }
}
