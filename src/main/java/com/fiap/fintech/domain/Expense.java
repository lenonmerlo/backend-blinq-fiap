package com.fiap.fintech.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "GASTO")
@SequenceGenerator(
        name = "GASTO_SEQ_GEN",
        sequenceName = "GASTO_SEQ",
        allocationSize = 1
)
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GASTO_SEQ_GEN")
    @Column(name = "ID_GASTO", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "DATA_GASTO", nullable = false)
    private LocalDate date;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @DecimalMin("0.01")
    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotBlank
    @Lob
    @Column(name = "DESCRICAO", nullable = false, columnDefinition = "CLOB")
    private String description;

    @NotBlank
    @Size(max = 30)
    @Column(name = "FORMA_PAGAMENTO", nullable = false, length = 30)
    private String paymentMethod;

    public Expense() {}

    public Expense(Long id, LocalDate date, BigDecimal amount, String description, String paymentMethod) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense expense)) return false;
        return Objects.equals(id, expense.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
