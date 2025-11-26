package com.example.umc9th.domain.participation.controller;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.participation.dto.ParticipationResDTO;
import com.example.umc9th.domain.participation.service.ParticipationService;
import com.example.umc9th.global.apipayload.ApiResponse;
import com.example.umc9th.global.apipayload.exception.code.MissionSuccessCode;
import com.example.umc9th.global.apipayload.exception.code.ParticipationSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class ParticipationController implements ParticipationControllerDocs{

    private final ParticipationService participationService;

    @PostMapping("/{missionId}/participate")
    public ApiResponse<ParticipationResDTO.PreviewDTO> addParticipation(
            @RequestHeader("X-USER-ID") Long memberId, // 임시: 실제 인증 방식과 연동 예정
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(ParticipationSuccessCode.FOUND, participationService.addParticipation(memberId, missionId));
    }

    @GetMapping("/participation/ongoing")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getOngoingParticipation(
            @RequestHeader("X-USER-ID") Long memberId, // 임시: 실제 인증 방식과 연동 예정
            @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, participationService.getOngoingParticipation(memberId, page));
    }

    @PatchMapping("/mission/{missionId}/complete")
    public ApiResponse<ParticipationResDTO.PreviewDTO> completeMission(
            @RequestHeader("X-USER-ID") Long memberId, // 임시 헤더로 받기
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, participationService.completeParticipation(memberId, missionId));
    }
}
