package com.fiap.fintech.domain;

import java.math.BigDecimal;

public class Investment {
    private Long id;
    private String productName;
    private BigDecimal amount;
    private BigDecimal annualRate;

    public Investment() {}

    public Investment(Long id, String productName, BigDecimal amount, BigDecimal annualRate) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.annualRate = annualRate;
    }

    public void applyYield() {
        System.out.println("Applying yield for " + productName +
                " on amount " + amount + " at annual rate " + annualRate);
    }

    public void addContribution(BigDecimal value) {
        System.out.println("Adding contribution for " + value + " to investment " + productName);
    }

    public void redeem(BigDecimal value) {
        System.out.println("Redeeming " + value + " from investment " + productName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(BigDecimal annualRate) {
        this.annualRate = annualRate;
    }
}
