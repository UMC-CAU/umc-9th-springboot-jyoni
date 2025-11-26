package com.example.umc9th.domain.participation.dto;

import lombok.Builder;

public class ParticipationResDTO {

    @Builder
    public record PreviewDTO(
            Long id,
            Long memberId,
            String participateStatus,
            Long missionId
    ) {}
}
