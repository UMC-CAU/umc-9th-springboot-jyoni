package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.common.entity.Dong;
import com.example.umc9th.domain.common.entity.Food;
import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.entity.StoreImage;

public class StoreConverter {

    public static Store toStore(StoreReqDTO.AddDTO dto, Dong dong, Food food) {
        Store store = Store.builder()
                .name(dto.name())
                .address(dto.address())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .isOpen(dto.isOpen())
                .dong(dong)
                .food(food)
                .build();

        // 이미지가 있다면 StoreImage 변환
        if (dto.imageUrls() != null && !dto.imageUrls().isEmpty()) {
            for (String url : dto.imageUrls()) {
                StoreImage storeImage = StoreImage.builder()
                        .imageUrl(url)
                        .store(store)
                        .build();

                store.getImages().add(storeImage);
            }
        }

        return store;
    }

    public static StoreResDTO.AddDTO toAddDTO(Store store) {
        return new StoreResDTO.AddDTO(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getLatitude(),
                store.getLongitude(),
                store.getIsOpen(),
                store.getAverageRating(),
                store.getReviewCount(),
                store.getDong().getName(),
                store.getFood().getName(),
                store.getImages().stream()
                        .map(image -> image.getImageUrl())
                        .toList()
        );
    }
}
