package com.example.umc9th.domain.participation.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.participation.entity.Participation;
import com.example.umc9th.domain.participation.enums.ParticipateStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    // 진행 중, 진행 완료한 미션 모아서 보는 쿼리 (페이징 포함)
    Page<Participation> findByMemberAndStatus(Member member, ParticipateStatus status, Pageable pageable);

    @Query("""
        SELECT p FROM Participation p
        JOIN FETCH p.mission
        WHERE p.member = :member AND p.status = 'ONGOING'
    """)
    Page<Participation> findOngoingParticipation(@Param("member") Member member, Pageable pageRequest);

    Participation findByMemberAndMission(Member member, Mission mission);
}
