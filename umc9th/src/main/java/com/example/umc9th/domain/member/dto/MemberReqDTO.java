package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO (
        String name,
        String email,
        Gender gender,
        LocalDate birth,
        String address,
        List<Long> preferCategory
    ){}
}
