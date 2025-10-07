package com.example.umc9th.domain.inquiry.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryType {
    GENERAL("일반 문의"),
    BUG_REPORT("버그 제보"),
    ACCOUNT("계정 관련"),
    SUGGESTION("제안"),
    COMPLAINT("불만/신고"),
    ETC("기타");

    private final String name;
}
