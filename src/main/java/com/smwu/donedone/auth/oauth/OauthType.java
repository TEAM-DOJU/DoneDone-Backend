package com.smwu.donedone.auth.oauth;

import com.smwu.donedone.auth.exception.NotFoundOauthTypeException;
import java.util.Arrays;

public enum OauthType {
    GOOGLE("google"),
    APPLE("apple");

    private String oauthType;

    OauthType(final String oauthType) {
        this.oauthType = oauthType;
    }

    public static OauthType of(final String oauthName) {
        return Arrays.stream(OauthType.values())
                .filter(it -> it.oauthType.equals(oauthName))
                .findFirst()
                .orElseThrow(() -> new NotFoundOauthTypeException(oauthName + " 은 지원하지 않는 Oauth 입니다."));
    }
}
