package com.smwu.donedone.auth;

import com.smwu.donedone.auth.exception.NotFoundAuthorityException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Authority {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String code;

    Authority(String code) {
        this.code = code;
    }

    public Authority getAuthority(final String role) {
        return Arrays.stream(Authority.values())
                .filter(it -> it.name().equals(role))
                .findFirst()
                .orElseThrow(() -> new NotFoundAuthorityException("해당 권한 내용을 찾을 수 없습니다."));
    }
}
