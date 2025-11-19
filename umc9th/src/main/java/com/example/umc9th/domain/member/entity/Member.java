package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.common.entity.BaseEntity;
import com.example.umc9th.domain.common.entity.Dong;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SnsProvider;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
    private String email;

    @Column(length = 20, unique = true)
    private String phoneNumber;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SnsProvider snsProvider;

    @Column(nullable = false, unique = true)
    private String snsUid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender = Gender.NONE;

    @Column
    private LocalDateTime deletedAt;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTermsAgreement> memberTermsAgreementList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dong_id")
    private Dong dong;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPreferredFood> memberPreferredFoodList = new ArrayList<>();
}
