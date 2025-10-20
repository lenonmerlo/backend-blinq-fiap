package com.fiap.fintech.repository;

import com.fiap.fintech.domain.Investment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    Page<Investment> findByProductContainingIgnoreCase(String product, Pageable pageable);
}
