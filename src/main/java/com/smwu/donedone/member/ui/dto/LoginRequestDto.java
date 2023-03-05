package com.smwu.donedone.member.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    private final String accessToken;
    private final String idToken;
}
