package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.common.entity.Dong;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SnsProvider;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SnsProvider snsProvider;

    private String snsUid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTermsAgreement> userTermsAgreementList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dong_id")
    private Dong dong;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPreferredFood> userPreferredFoodList = new ArrayList<>();
}
