package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionPreviewDTO (
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

    @Builder
    public record MissionPreviewListDTO(
            List<MissionResDTO.MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}
}
