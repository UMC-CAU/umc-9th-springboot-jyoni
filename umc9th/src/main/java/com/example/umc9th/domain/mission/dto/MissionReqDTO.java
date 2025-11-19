package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

public class MissionReqDTO {

    @Builder
    public record AddDTO (
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
