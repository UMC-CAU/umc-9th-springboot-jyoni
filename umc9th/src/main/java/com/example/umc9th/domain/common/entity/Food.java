package com.example.umc9th.domain.common.entity;

import com.example.umc9th.domain.member.entity.UserPreferredFood;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "food")
    private List<UserPreferredFood> preferredByUsers = new ArrayList<>();

    @OneToMany(mappedBy = "food")
    private List<Dong> dongList = new ArrayList<>();
}
