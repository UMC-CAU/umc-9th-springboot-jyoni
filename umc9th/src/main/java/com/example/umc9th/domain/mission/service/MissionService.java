package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apipayload.exception.StoreException;
import com.example.umc9th.global.apipayload.exception.code.StoreErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public MissionResDTO.MissionPreviewDTO addMission(MissionReqDTO.AddDTO dto, Long storeId) {
        // 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(dto, store);
        missionRepository.save(mission);

        return MissionConverter.toPreviewDTO(mission);
    }

    public MissionResDTO.MissionPreviewListDTO getMissionByStore(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toMissionPreviewList(result);
    }

    public MissionResDTO.MissionPreviewListDTO getMissionOngoing(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> result = missionRepository.findValidActiveMission(pageRequest, LocalDate.now());

        return MissionConverter.toMissionPreviewList(result);
    }
}
