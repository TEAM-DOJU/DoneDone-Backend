package com.smwu.donedone.member.domain.dto;

import com.smwu.donedone.interest.domain.Interest;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InterestDto {

    private String value;

    public InterestDto(final String value) {
        this.value = value;
    }

    public static InterestDto of(Interest interest) {
        return new InterestDto(interest.getTitle());
    }
}
