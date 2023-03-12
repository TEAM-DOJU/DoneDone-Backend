package com.smwu.donedone.member.domain.repository;

import com.smwu.donedone.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(final String email);
    Member findByUserId(final String id);
}
