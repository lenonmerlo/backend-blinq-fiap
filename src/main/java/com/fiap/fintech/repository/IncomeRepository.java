package com.fiap.fintech.repository;

import com.fiap.fintech.domain.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Page<Income> findBySourceContainingIgnoreCase(String source, Pageable pageable);
}