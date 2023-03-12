package com.smwu.donedone.member.domain;

import com.smwu.donedone.auth.Authority;
import com.smwu.donedone.auth.oauth.OauthType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String email;
    private String name;
    private String interestIds;
    private Authority authority;
    private OauthType oauthType;

    public Member(String email) {
        this.email = email;
    }

    public Member( String userId, String email, String name, String interestIds,
                  Authority authority, OauthType oauthType) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.interestIds = interestIds;
        this.authority = authority;
        this.oauthType = oauthType;
    }
}
