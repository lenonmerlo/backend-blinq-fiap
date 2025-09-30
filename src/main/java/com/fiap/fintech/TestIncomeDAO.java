package com.fiap.fintech;

import com.fiap.fintech.dao.IncomeDAO;
import com.fiap.fintech.domain.Income;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestIncomeDAO {
    public static void main(String[] args) {
        IncomeDAO dao = new IncomeDAO();

        // --- INSERT de 5 registros ---
        dao.insert(new Income(106L, LocalDate.now(), new BigDecimal("5000.00"), "Salary", "Company A"));
        dao.insert(new Income(107L, LocalDate.now().minusDays(1), new BigDecimal("150.00"), "Freelance work", "Client B"));
        dao.insert(new Income(108L, LocalDate.now().minusDays(2), new BigDecimal("200.00"), "Gift", "Friend"));
        dao.insert(new Income(109L, LocalDate.now().minusDays(3), new BigDecimal("50.00"), "Dividend", "Brokerage"));
        dao.insert(new Income(110L, LocalDate.now().minusDays(4), new BigDecimal("1200.00"), "Bonus", "Company A"));

        // --- SELECT ALL ---
        List<Income> all = dao.getAll();
        System.out.println("Total incomes: " + all.size());
        all.forEach(System.out::println);
    }
}
