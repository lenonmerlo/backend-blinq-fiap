package com.fiap.fintech.service;

import com.fiap.fintech.domain.Expense;
import com.fiap.fintech.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class ExpenseService {
    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    public Page<Expense> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Expense getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Gasto não encontrado."));
    }

    public Expense create(Expense e) {
        e.setId(null);
        validate(e);
        return repo.save(e);
    }

    public Expense update(Long id, Expense req) {
        Expense e = getOrThrow(id);
        e.setDate(req.getDate());
        e.setAmount(req.getAmount());
        e.setDescription(req.getDescription());
        e.setPaymentMethod(req.getPaymentMethod());
        e.setPaymentMethod(req.getPaymentMethod());
        validate(e);
        return repo.save(e);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("Gasto não encontrado");
        repo.deleteById(id);
    }

    private void validate(Expense e) {
        if (e.getAmount() == null || e.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        if (e.getDate() == null)
            throw new IllegalArgumentException("Data do gasto é obrigatória");
    }


}
