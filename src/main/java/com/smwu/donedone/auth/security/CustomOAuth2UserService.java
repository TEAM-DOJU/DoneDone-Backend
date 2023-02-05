package com.smwu.donedone.auth.security;

import com.smwu.donedone.auth.oauth.OauthFactory;
import com.smwu.donedone.member.domain.Member;
import com.smwu.donedone.member.domain.repository.MemberRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OauthFactory oauthFactory;
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest userRequest) {
        final OAuth2UserService delegate = new DefaultOAuth2UserService();
        final OAuth2User oAuth2User = delegate.loadUser(userRequest);

        final ClientRegistration clientRegistration = userRequest.getClientRegistration();
        final String oauthName = clientRegistration.getRegistrationId();
        final String userNameAttributeName = clientRegistration.getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        if (!memberRepository.findByEmail(oAuth2User.getAttribute("email")).isPresent()) {
            memberRepository.save(new Member(oAuth2User.getAttribute("email")));
        }

        return oauthFactory.createOauthUser(oauthName, attributes);
    }
}
