package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 유저 마이페이지 화면 쿼리
    User findUserById(Long id);

}
