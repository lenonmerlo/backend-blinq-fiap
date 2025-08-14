package com.fiap.fintech.domain;

import java.time.LocalDate;

public class Client extends Person{
    private LocalDate registrationDate;

    public Client() {}

    public Client(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Client(Long id, String fullName, String cpf, String email, LocalDate registrationDate) {
        super(id, fullName, cpf, email);
        this.registrationDate = registrationDate;
    }

    @Override
    public void updateProfile() {
        System.out.println("Updating client profile: " + getFullName());
    }

    @Override
    public void activate() {
        System.out.println("Activating client: " + getFullName());
    }

    @Override
    public void deactivate() {
        System.out.println("Deactivating client");
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
