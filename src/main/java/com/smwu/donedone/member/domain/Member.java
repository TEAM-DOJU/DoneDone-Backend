package com.smwu.donedone.member.domain;

import com.smwu.donedone.auth.Authority;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String interestIds;
    private Authority authority;

    public Member(String email) {
        this.email = email;
    }
}
