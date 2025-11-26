package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.mission.enums.RewardType;

import java.time.LocalDate;

public class MissionConverter {

    public static Mission toMission(MissionReqDTO.AddDTO dto, Store store) {
        return Mission.builder()
                .rewardType(RewardType.valueOf(dto.rewardType()))
                .rewardValue(dto.rewardValue())
                .missionCondition(dto.missionCondition())
                .missionCode(dto.missionCode())
                .isActive(dto.isActive())
                .validFrom(LocalDate.parse(dto.validFrom()))
                .validTo(LocalDate.parse(dto.validTo()))
                .store(store)
                .build();
    }

    public static MissionResDTO.MissionPreviewDTO toPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .id(mission.getId())
                .rewardType(mission.getRewardType().toString())
                .rewardValue(mission.getRewardValue())
                .missionCondition(mission.getMissionCondition())
                .missionCode(mission.getMissionCode())
                .isActive(mission.getIsActive())
                .validFrom(mission.getValidFrom().toString())
                .validTo(mission.getValidTo().toString())
                .storeId(mission.getStore().getId())
                .build();
    }
}
