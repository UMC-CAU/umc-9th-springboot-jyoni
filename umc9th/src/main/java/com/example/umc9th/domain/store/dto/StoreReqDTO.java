package com.example.umc9th.domain.store.dto;

import java.math.BigDecimal;
import java.util.List;

public class StoreReqDTO {

    public record AddDTO(
            String name,
            String address,
            BigDecimal latitude,
            BigDecimal longitude,
            Boolean isOpen,
            Long dongId,
            Long foodId,
            List<String> imageUrls
    ) {}
}
