package com.example.umc9th.domain.terms.entity;

import com.example.umc9th.domain.terms.enums.TermType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "terms")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TermType termType;

    @Column(nullable = false)
    private String version;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isMandatory;

    private LocalDate effectiveDate;
}
