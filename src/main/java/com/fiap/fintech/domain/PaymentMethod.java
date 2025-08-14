package com.fiap.fintech.domain;

public class PaymentMethod {
    private Long id;
    private String label;
    private String number;

    public PaymentMethod() {    }

    public PaymentMethod(Long id, String label, String number) {
        this.id = id;
        this.label = label;
        this.number = number;
    }

    public void details() {
        System.out.println("Payment method: " + label + " (" + number + ")");
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
}
