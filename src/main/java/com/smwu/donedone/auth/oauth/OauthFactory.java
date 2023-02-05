package com.smwu.donedone.auth.oauth;

import java.util.Map;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class OauthFactory {
    public OAuth2User createOauthUser(final String oauthName,
                                      Map<String, Object> attributes){
        final OauthType oauthType = OauthType.of(oauthName);

        switch (oauthType) {
            case GOOGLE:
                return new GoogleUser(attributes);
            case APPLE:
                return new AppleUser(attributes);
            default:
                throw new IllegalArgumentException("회원을 만들 수 없습니다.");
        }
    }
}
