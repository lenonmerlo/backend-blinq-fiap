package com.fiap.fintech.domain;

import java.time.LocalDate;

public class Client {
    private Long id;
    private String fullName;
    private String cpf;
    private String email;
    private LocalDate registrationDate;

    public Client() {}

    public Client(Long id, String fullName, String cpf, String email, LocalDate registrationDate) {
            this.id = id;
            this.fullName = fullName;
            this.cpf = cpf;
            this.email = email;
            this.registrationDate = registrationDate;
    }

    public void updateProfile() {
        System.out.println("Updating client profile: " + fullName);
    }
    public void activate() {
        System.out.println("Activating client: " + fullName);
    }
    public void deactivate() {
        System.out.println("Deactivating client");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
