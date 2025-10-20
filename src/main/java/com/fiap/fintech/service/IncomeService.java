package com.fiap.fintech.service;

import com.fiap.fintech.domain.Income;
import com.fiap.fintech.repository.IncomeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class IncomeService {
    private final IncomeRepository repo;

    public IncomeService(IncomeRepository repo) {
        this.repo = repo;
    }

    public Page<Income> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Income getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Recebimento não encontrado."));
    }

    public Income create(Income i) {
        i.setId(null);
        validate(i);
        return repo.save(i);
    }

    public Income update(Long id, Income req) {
        Income i = getOrThrow(id);
        i.setDate(req.getDate());
        i.setAmount(req.getAmount());
        i.setDescription(req.getDescription());
        i.setSource(req.getSource());
        validate(i);
        return repo.save(i);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("Recebimento não encontrado");
        repo.deleteById(id);
    }

    private void validate(Income i) {
        if (i.getAmount() == null || i.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        if (i.getDate() == null)
            throw new IllegalArgumentException("Data do recebimento é obrigatória");
    }
}