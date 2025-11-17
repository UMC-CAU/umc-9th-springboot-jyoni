package com.example.umc9th.domain.test.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;
import com.example.umc9th.global.apipayload.exception.GeneralException;

public class TestException extends GeneralException {

    public TestException(BaseErrorCode code) {
        super(code);
    }
}
