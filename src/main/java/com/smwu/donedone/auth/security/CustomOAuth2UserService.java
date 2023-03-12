package com.smwu.donedone.auth.security;

import com.smwu.donedone.auth.Authority;
import com.smwu.donedone.auth.handler.OAuthProviderMissMatchException;
import com.smwu.donedone.auth.oauth.OAuth2UserInfo;
import com.smwu.donedone.auth.oauth.OAuth2UserInfoFactory;
import com.smwu.donedone.auth.oauth.OauthType;
import com.smwu.donedone.member.domain.Member;
import com.smwu.donedone.member.domain.exception.NotFoundMemberException;
import com.smwu.donedone.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        OauthType providerType = OauthType
                .valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        Member savedUser = memberRepository.findByUserId(userInfo.getId());

        if (savedUser != null) {
            if (providerType != savedUser.getOauthType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                                " account. Please use your " + savedUser.getOauthType() + " account to login."
                );
            }
            updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private Member createUser(OAuth2UserInfo userInfo, OauthType providerType) {
        Member user = new Member(
                userInfo.getId(),
                userInfo.getEmail(),
                userInfo.getName(),
                null,
                Authority.USER,
                providerType
        );

        return memberRepository.saveAndFlush(user);
    }

    private Member updateUser(Member user, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !user.getName().equals(userInfo.getName())) {
            user.setName(userInfo.getName());
        }

        return user;
    }
}
