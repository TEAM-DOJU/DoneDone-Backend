package com.smwu.donedone.done.domain.repository;

import com.smwu.donedone.done.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
