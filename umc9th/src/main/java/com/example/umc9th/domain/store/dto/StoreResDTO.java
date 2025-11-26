package com.example.umc9th.domain.store.dto;

import java.math.BigDecimal;
import java.util.List;

public class StoreResDTO {

    public record AddDTO(
            Long id,
            String name,
            String address,
            BigDecimal latitude,
            BigDecimal longitude,
            Boolean isOpen,
            BigDecimal averageRating,
            Long reviewCount,
            String dongName,
            String foodName,
            List<String> imageUrls
    ) {}
}
