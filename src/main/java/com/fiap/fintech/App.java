package com.fiap.fintech;

import com.fiap.fintech.domain.Account;
import com.fiap.fintech.domain.Client;
import com.fiap.fintech.domain.CreditCard;
import com.fiap.fintech.domain.Investment;

import java.math.BigDecimal;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    public static void main(String[] args) {
        Client client1 = new Client(
                1L,
                "Lenon Merlo",
                "123.456.789-00",
                "lenon@example.com",
                LocalDate.now()
        );

        client1.updateProfile();
        client1.activate();
        client1.deactivate();

        System.out.println("Client name: " + client1.getFullName());
        System.out.println("Email: " + client1.getEmail());

        client1.setEmail("newemail@example.com");
        System.out.println("Updated Email: " + client1.getEmail());

        Account acc1 = new Account(1L, "Blinq Bank", "0001", "12345-6", new BigDecimal("1500.00"));
        Account acc2 = new Account(2L, "Other Bank", "0001", "99996-9", new BigDecimal("0.00"));

        acc1.deposit(new BigDecimal("200.00"));
        acc1.withdraw(new BigDecimal("50.00"));
        acc1.transfer(acc2, new BigDecimal("100.00"));

        System.out.println("Account number: " + acc1.getNumber());
        System.out.println(acc1.getBank());

        CreditCard card = new CreditCard(
                1L, "Blinq Visa", "4111111111111111", 12, 2023, new BigDecimal("2500.00")
        );
        card.pay(new BigDecimal("89.90"), "Online purchase");
        card.generateInvoice();
        card.increaseLimit(new BigDecimal("500.00"));

        System.out.println("Card label: " + card.getLabel());
        System.out.println("Card limit: " + card.getLimitAmount());

        Investment inv = new Investment(
                1L, "CDB 100% CDI", new BigDecimal("1000.00"), new BigDecimal("0.12")
        );
        inv.applyYield();
        inv.addContribution(new BigDecimal("200.00"));
        inv.redeem(new BigDecimal("300.00"));

        System.out.println("Investment product " + inv.getProductName());
        System.out.println("Annual rate: " + inv.getAnnualRate());

    }
}