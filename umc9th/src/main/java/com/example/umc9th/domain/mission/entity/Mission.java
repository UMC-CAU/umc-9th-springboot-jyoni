package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.mission.enums.RewardType;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RewardType rewardType;

    @Column(nullable = false)
    private Long rewardValue;

    @Column(nullable = false, length = 100)
    private String condition;

    @Column(nullable = false, length = 20, unique = true)
    private String missionCode;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private LocalDate validFrom;

    @Column(nullable = false)
    private LocalDate validTo;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
