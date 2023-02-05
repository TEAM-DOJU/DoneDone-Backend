package com.smwu.donedone.interest.domain.repository;

import com.smwu.donedone.interest.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
