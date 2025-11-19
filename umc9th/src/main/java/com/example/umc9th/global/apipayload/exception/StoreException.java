package com.example.umc9th.global.apipayload.exception;

import com.example.umc9th.global.apipayload.code.BaseErrorCode;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
