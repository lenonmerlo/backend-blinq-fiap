package com.fiap.fintech.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Income {
    private Long id;
    private LocalDate date;
    private BigDecimal amount;
    private String description;
    private String source;

    public Income() {}

    public Income(Long id, LocalDate date, BigDecimal amount, String description, String source) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Income)) return false;
        Income income = (Income) o;
        return Objects.equals(id, income.id);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
