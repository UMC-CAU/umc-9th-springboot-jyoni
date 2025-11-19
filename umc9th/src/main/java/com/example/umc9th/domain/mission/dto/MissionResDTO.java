package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record AddDTO (
            Long id,
            String rewardType,
            Long rewardValue,
            String missionCondition,
            String missionCode,
            boolean isActive,
            String validFrom,
            String validTo,
            Long storeId
    ) {}
}
