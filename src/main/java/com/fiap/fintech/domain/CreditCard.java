package com.fiap.fintech.domain;

import java.math.BigDecimal;

public class CreditCard {
    private Long id;
    private String label;
    private String number;
    private int expiryMonth;
    private int expiryYear;
    private BigDecimal limitAmount;

    public CreditCard() {}

    public CreditCard(Long id, String label, String number, int expiryMonth, int expiryYear, BigDecimal limitAmount) {
        this.id = id;
        this.label = label;
        this.number = number;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.limitAmount = limitAmount;
    }

    public void pay(BigDecimal amount, String description) {
        System.out.println("Paying " + amount + " with card " + label + ": " + description);
    }

    public void generateInvoice() {
        System.out.println("Generating invoice for card: " + label);
    }

    public void increaseLimit(BigDecimal amount) {
        System.out.println("Requesting limit increase of " + amount + " for card: " + label);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
