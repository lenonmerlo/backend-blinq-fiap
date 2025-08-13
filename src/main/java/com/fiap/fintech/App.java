package com.fiap.fintech;

import com.fiap.fintech.domain.Client;

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
    }
}