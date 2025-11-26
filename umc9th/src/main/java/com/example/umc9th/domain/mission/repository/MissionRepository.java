package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.common.entity.Dong;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 현재 선택된 지역에서 도전익 가능한 목록 (페이징 포함)
    @Query("""
        SELECT m FROM Mission m
        JOIN m.store s
        WHERE s.dong = :dong
        AND m.isActive = true
        AND :now BETWEEN m.validFrom AND m.validTo
    """)
    Page<Mission> findAvailableMissions(@Param("dong") Dong dong, @Param("now") LocalDate now, Pageable pageable);

    // Active && ValidFrom <= now <= validTo
    @Query("""
        SELECT m FROM Mission m
        WHERE m.id = :id
        AND m.isActive = true
        AND :now BETWEEN m.validFrom AND m.validTo
    """)
    Optional<Mission> findValidActiveMissionByMemberId(@Param("id") Long id, @Param("now") LocalDate now);

    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}
