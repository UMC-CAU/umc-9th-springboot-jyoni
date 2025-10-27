package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.common.entity.BaseEntity;
import com.example.umc9th.domain.terms.entity.Terms;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "userTermsAgreement")
public class UserTermsAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id")
    private Terms terms;

    @Column(nullable = false)
    private boolean isAgreed;
}
