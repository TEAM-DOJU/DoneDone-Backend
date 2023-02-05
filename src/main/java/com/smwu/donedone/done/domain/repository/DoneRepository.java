package com.smwu.donedone.done.domain.repository;

import com.smwu.donedone.done.domain.Done;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DoneRepository extends JpaRepository<Done,Long> {

    List<Done> findByDateBetween(@Param(value = "startDay") LocalDateTime startDay, @Param(value = "lastDay") LocalDateTime lastDay);
}
