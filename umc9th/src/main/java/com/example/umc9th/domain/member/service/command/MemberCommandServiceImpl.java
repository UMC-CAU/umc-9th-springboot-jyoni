package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.common.entity.Food;
import com.example.umc9th.domain.common.repository.FoodRepository;
import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.MemberPreferredFood;
import com.example.umc9th.domain.member.repository.MemberPreferredFoodRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.apipayload.exception.FoodException;
import com.example.umc9th.global.apipayload.exception.code.FoodErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberPreferredFoodRepository memberPreferredFoodRepository;
    private final FoodRepository foodRepository;

    @Override
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {
        // 사용자 생성
        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

        if (dto.preferCategory().size() > 1) {
            List<MemberPreferredFood> memberPreferredFoodList = new ArrayList<>();

            for (Long id : dto.preferCategory()) {
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                MemberPreferredFood memberPreferredFood = MemberPreferredFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                memberPreferredFoodList.add(memberPreferredFood);
            }
            memberPreferredFoodRepository.saveAll(memberPreferredFoodList);
        }
        return MemberConverter.toJoinDTO(member);
    }

}
