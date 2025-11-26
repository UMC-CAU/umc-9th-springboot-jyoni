package com.example.umc9th.domain.participation.controller;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.participation.dto.ParticipationResDTO;
import com.example.umc9th.global.apipayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface ParticipationControllerDocs {

    @Operation(
            summary = "내가 진행 중인 미션 목록 조회 API",
            description = "내가 진행 중인 미션을 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.MissionPreviewListDTO> getOngoingParticipation(
            @RequestHeader("X-USER-ID") Long memberId,
            @RequestParam Integer page
    );

    @Operation(
            summary = "진행 중인 미션 완료 처리 API",
            description = "내가 진행 중인 미션을 완료 처리합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ParticipationResDTO.PreviewDTO> completeMission(
            @RequestHeader("X-USER-ID") Long memberId, // 임시 헤더로 받기
            @PathVariable Long missionId
    );
}
