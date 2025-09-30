package com.fiap.fintech.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Investment {
    private Long id;
    private LocalDate applicationDate;
    private BigDecimal amount;
    private BigDecimal annualRate;
    private Integer term;
    private String productName;

    public Investment() {}

    public Investment(Long id, LocalDate applicationDate, BigDecimal amount,
                      BigDecimal annualRate, Integer term, String productName) {
        this.id = id;
        this.applicationDate = applicationDate;
        this.amount = amount;
        this.annualRate = annualRate;
        this.term = term;
        this.productName = productName;
    }

    public void applyYield() {
        System.out.println("Aplicando rendimento para " + productName +
                " - valor " + amount + " à taxa " + annualRate);
    }

    public void addContribution(BigDecimal value) {
        System.out.println("Adicionando " + value + " ao investimento " + productName);
        this.amount = this.amount.add(value);
    }

    public void redeem(BigDecimal value) {
        System.out.println("Resgatando " + value + " do investimento " + productName);
        this.amount = this.amount.subtract(value);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getAnnualRate() { return annualRate; }
    public void setAnnualRate(BigDecimal annualRate) { this.annualRate = annualRate; }

    public Integer getTerm() { return term; }
    public void setTerm(Integer term) { this.term = term; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    @Override
    public String toString() {
        return "Investment{" +
                "id=" + id +
                ", applicationDate=" + applicationDate +
                ", amount=" + amount +
                ", annualRate=" + annualRate +
                ", term=" + term +
                ", productName='" + productName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Investment that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
