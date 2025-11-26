package com.example.umc9th.domain.participation.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.participation.dto.ParticipationResDTO;
import com.example.umc9th.domain.participation.entity.Participation;
import com.example.umc9th.domain.participation.enums.ParticipateStatus;

public class ParticipationConverter {

    public static Participation toParticipation(Member member, Mission mission) {
        return Participation.builder()
                .status(ParticipateStatus.ONGOING)
                .member(member)
                .mission(mission)
                .build();
    }

    public static ParticipationResDTO.AddDTO toAddDTO(Participation participation) {
        return ParticipationResDTO.AddDTO.builder()
                .id(participation.getId())
                .memberId(participation.getMember().getId())
                .participateStatus(participation.getStatus().name())
                .missionId(participation.getMission().getId())
                .build();
    }
}
