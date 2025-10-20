package com.fiap.fintech.service;

import com.fiap.fintech.domain.Investment;
import com.fiap.fintech.repository.InvestmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class InvestmentService {
    private final InvestmentRepository repo;
    public InvestmentService(InvestmentRepository repo){ this.repo = repo; }

    public Page<Investment> list(int page,int size){ return repo.findAll(PageRequest.of(page,size)); }
    public Investment getOrThrow(Long id){ return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Investimento não encontrado")); }

    public Investment create(Investment i){
        i.setId(null);
        validate(i);
        return repo.save(i);
    }
    public Investment update(Long id, Investment req){
        Investment i = getOrThrow(id);
        i.setApplicationDate(req.getApplicationDate());
        i.setAmountApplied(req.getAmountApplied());
        i.setAnnualRate(req.getAnnualRate());
        i.setMonths(req.getMonths());
        i.setProduct(req.getProduct());
        validate(i);
        return repo.save(i);
    }
    public void delete(Long id){
        if(!repo.existsById(id)) throw new NoSuchElementException("Investimento não encontrado");
        repo.deleteById(id);
    }

    private void validate(Investment i){
        if(i.getAmountApplied()==null || i.getAmountApplied().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor aplicado deve ser maior que zero");
        if(i.getApplicationDate()==null) throw new IllegalArgumentException("Data de aplicação é obrigatória");
        if(i.getMonths()==null || i.getMonths() < 1) throw new IllegalArgumentException("Prazo (meses) inválido");
        if(i.getAnnualRate()==null || i.getAnnualRate().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Taxa anual inválida");
    }
}
