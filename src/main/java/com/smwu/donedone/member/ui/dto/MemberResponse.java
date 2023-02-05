package com.smwu.donedone.member.ui.dto;

import com.smwu.donedone.member.domain.Member;
import com.smwu.donedone.member.domain.dto.InterestDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private String email;
    private String name;
    private List<String> interests;

    public static MemberResponse of(Member member, List<InterestDto> interestDtos) {
        final List<String> interests = interestDtos.stream()
                .map(InterestDto::getValue)
                .collect(Collectors.toList());
        return new MemberResponse(member.getEmail(), member.getName(), interests);
    }
}
