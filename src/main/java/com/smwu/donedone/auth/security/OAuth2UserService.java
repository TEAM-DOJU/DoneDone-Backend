package com.smwu.donedone.auth.security;

import com.smwu.donedone.member.domain.repository.MemberRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    public OAuth2UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest userRequest){
        final OAuth2User oAuth2User = super.loadUser(userRequest);
        // TODO: 전략
        // userRequest.getClientRegistration().getRegistrationId().
        return null;
    }
}
