package com.smwu.donedone.member.domain.service;

import com.smwu.donedone.member.domain.dto.InterestDto;
import com.smwu.donedone.member.domain.exception.NotFoundInterestException;
import com.smwu.donedone.interest.domain.Interest;
import com.smwu.donedone.interest.domain.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInterestService {
    private final InterestRepository interestRepository;

    public InterestDto getInterest(final Long interestId){
        final Interest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new NotFoundInterestException("해당 관심사를 찾을 수 없습니다."));
        return InterestDto.of(interest);
    }
}
