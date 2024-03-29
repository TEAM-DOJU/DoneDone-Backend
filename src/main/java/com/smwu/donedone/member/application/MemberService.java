package com.smwu.donedone.member.application;

import com.smwu.donedone.member.application.exception.NotFoundMemberException;
import com.smwu.donedone.member.domain.Member;
import com.smwu.donedone.member.domain.dto.InterestDto;
import com.smwu.donedone.member.domain.repository.MemberRepository;
import com.smwu.donedone.member.domain.service.MemberInterestService;
import com.smwu.donedone.member.ui.dto.LoginRequestDto;
import com.smwu.donedone.member.ui.dto.MemberResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberInterestService memberInterestService;
    private final MemberRepository memberRepository;

    public MemberResponse findMember(Long id) {
        final Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundMemberException("해당 멤버를 찾을 수 없습니다."));
        List<InterestDto> interests = null;
        if(member.getInterestIds() != null){
            interests = Arrays.stream(member.getInterestIds().split(","))
                    .mapToLong(Long::parseLong)
                    .mapToObj(memberInterestService::getInterest)
                    .collect(Collectors.toList());
        }
        return MemberResponse.of(member, interests);
    }

//    public LoginResponse login(final LoginRequestDto loginRequestDto) {
//        final String accessToken = loginRequestDto.getAccessToken();
//        final String idToken = loginRequestDto.getIdToken();
//
//
//    }
}
