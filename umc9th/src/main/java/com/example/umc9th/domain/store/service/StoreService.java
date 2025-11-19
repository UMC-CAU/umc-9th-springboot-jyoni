package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.common.entity.Dong;
import com.example.umc9th.domain.common.entity.Food;
import com.example.umc9th.domain.common.repository.DongRepository;
import com.example.umc9th.domain.common.repository.FoodRepository;
import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apipayload.exception.DongException;
import com.example.umc9th.global.apipayload.exception.FoodException;
import com.example.umc9th.global.apipayload.exception.code.DongErrorCode;
import com.example.umc9th.global.apipayload.exception.code.FoodErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final DongRepository dongRepository;
    private final FoodRepository foodRepository;

    public StoreResDTO.AddDTO addStore(StoreReqDTO.AddDTO dto) {
        // dong 조회
        Dong dong = dongRepository.findById(dto.dongId())
                .orElseThrow(() -> new DongException(DongErrorCode.NOT_FOUND));

        // food 조회
        Food food = foodRepository.findById(dto.foodId())
                .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

        // store 생성
        Store store = StoreConverter.toStore(dto, dong, food);
        storeRepository.save(store);

        return StoreConverter.toAddDTO(store);
    }
}
