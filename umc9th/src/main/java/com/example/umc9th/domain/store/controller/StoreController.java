package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.service.StoreService;
import com.example.umc9th.global.apipayload.ApiResponse;
import com.example.umc9th.global.apipayload.exception.code.StoreSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/dong/{dongId}/stores")
    public ApiResponse<StoreResDTO.AddDTO> addStore(
            @RequestBody StoreReqDTO.AddDTO dto,
            @PathVariable Long dongId
    ) {
        return ApiResponse.onSuccess(StoreSuccessCode.FOUND, storeService.addStore(dto, dongId));
    }
}
