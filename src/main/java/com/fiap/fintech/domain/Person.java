package com.fiap.fintech.domain;

public class Person {
    private Long id;
    private String fullName;
    private String cpf;
    private String email;

    public Person() {}

    public Person(Long id, String fullName, String cpf, String email) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
    }

    public void updateProfile() {
        System.out.println("Updating profile of: " + fullName);
    }

    public void activate() {
        System.out.println("Activating person: " + fullName);
    }

    public void deactivate() {
        System.out.println("Deactivating person: " + fullName);
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
}
