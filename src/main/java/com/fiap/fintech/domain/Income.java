package com.fiap.fintech.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "RECEBIMENTO")
@SequenceGenerator(
        name = "RECEBIMENTO_SEQ_GEN",
        sequenceName = "RECEBIMENTO_SEQ",
        allocationSize = 1
)
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEBIMENTO_SEQ_GEN")
    @Column(name = "ID_RECEBIMENTO", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "DATA_RECEBIMENTO", nullable = false)
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
    @Column(name = "ORIGEM", nullable = false, length = 30)
    private String source;

    public Income() {}

    public Income(Long id, LocalDate date, BigDecimal amount, String description, String source) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.source = source;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

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
        if (!(o instanceof Income income)) return false;
        return Objects.equals(id, income.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}