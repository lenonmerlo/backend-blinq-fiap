package com.fiap.fintech.domain;

import java.math.BigDecimal;

public class CreditCard extends PaymentMethod {
    private int expiryMonth;
    private int expiryYear;
    private BigDecimal limitAmount;

    public CreditCard() {}

    public CreditCard(int expiryMonth, int expiryYear, BigDecimal limitAmount) {
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.limitAmount = limitAmount;
    }

    public CreditCard(Long id, String label, String number, int expiryMonth, int expiryYear, BigDecimal limitAmount) {
        super(id, label, number);
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.limitAmount = limitAmount;
    }

    public void pay(BigDecimal amount, String description) {
        System.out.println("Paying " + amount + " with card " + getLabel() + ": " + description);
    }

    public void generateInvoice() {
        System.out.println("Generating invoice for card: " + getLabel());
    }

    public void increaseLimit(BigDecimal amount) {
        System.out.println("Requesting limit increase of " + amount + " for card: " + getLabel());
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }
}

