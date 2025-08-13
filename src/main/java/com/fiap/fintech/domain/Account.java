package com.fiap.fintech.domain;

import java.math.BigDecimal;

public class Account{
    private Long id;
    private String bank;
    private String branch;
    private String number;
    private BigDecimal balance;

    public Account() {}

    public Account(Long id, String bank, String branch, String number, BigDecimal balance) {
        this.id = id;
        this.bank = bank;
        this.branch = branch;
        this.number = number;
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) {
        System.out.println("Depositing " + amount + " into account " + number);
    }

    public void withdraw(BigDecimal amount) {
        System.out.println("Withdrawing " + amount + " from account " + number);
    }

    public void transfer(Account destination, BigDecimal amount) {
        System.out.println("Transferring " + amount + " from " + number + " to " + destination.getNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
