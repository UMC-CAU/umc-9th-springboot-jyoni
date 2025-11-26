package com.example.umc9th.domain.common.repository;

import com.example.umc9th.domain.common.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    public Food findById(long id);
}
