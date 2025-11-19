package com.example.umc9th.domain.participation.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.participation.converter.ParticipationConverter;
import com.example.umc9th.domain.participation.dto.ParticipationResDTO;
import com.example.umc9th.domain.participation.entity.Participation;
import com.example.umc9th.domain.participation.repository.ParticipationRepository;
import com.example.umc9th.global.apipayload.exception.MemberException;
import com.example.umc9th.global.apipayload.exception.MissionException;
import com.example.umc9th.global.apipayload.exception.code.MemberErrorCode;
import com.example.umc9th.global.apipayload.exception.code.MissionErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final ParticipationRepository participationRepository;

    public ParticipationResDTO.AddDTO addParticipation(Long memberId, Long missionId) {
        // 사용자 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 미션 조회
        Mission mission = missionRepository.findValidActiveMission(missionId, LocalDate.now())
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        Participation participation = ParticipationConverter.toParticipation(member, mission);
        participationRepository.save(participation);

        return ParticipationConverter.toAddDTO(participation);
    }
}
