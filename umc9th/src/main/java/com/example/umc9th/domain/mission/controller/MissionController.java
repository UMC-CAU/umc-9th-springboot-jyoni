package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apipayload.ApiResponse;
import com.example.umc9th.global.apipayload.exception.code.MissionSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs {

    private final MissionService missionService;

    @PostMapping("/store/{storeId}/mission")
    public ApiResponse<MissionResDTO.MissionPreviewDTO> addMission(
            @RequestBody @Valid MissionReqDTO.AddDTO dto,
            @PathVariable Long storeId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, missionService.addMission(dto, storeId));
    }

    @GetMapping("/store/{storeId}/mission")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMissionByStore(
            @PathVariable Long storeId,
            @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, missionService.getMissionByStore(storeId, page));
    }
}
