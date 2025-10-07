package com.example.umc9th.domain.terms.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TermType {

    AGE_VERIFICATION("만 14세 이상입니다."),
    TERMS_OF_SERVICE("서비스 이용약관"),
    PRIVACY_POLICY("개인 정보 처리 방침"),
    LOCATION_SERVICE("위치정보 제공"),
    MARKETING_CONSENT("마케팅 수신 동의");

    private final String title;
}
