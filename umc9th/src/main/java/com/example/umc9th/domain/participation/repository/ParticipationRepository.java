package com.example.umc9th.domain.participation.repository;

import com.example.umc9th.domain.member.entity.User;
import com.example.umc9th.domain.participation.entity.Participation;
import com.example.umc9th.domain.participation.enums.ParticipateStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    // 진행 중, 진행 완료한 미션 모아서 보는 쿼리 (페이징 포함)
    Page<Participation> findByUserAndStatusIn(User user, List<ParticipateStatus> statusList, Pageable pageable);
}
