package com.fiap.fintech.repository;

import com.fiap.fintech.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository <Expense, Long>{
    Page<Expense> findByPaymentMethodContainingIgnoreCase(String paymentMethod, Pageable pageable);
}
