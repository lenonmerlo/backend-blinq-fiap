package com.fiap.fintech.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "INVESTIMENTO")
@SequenceGenerator(name = "INVESTIMENTO_SEQ_GEN", sequenceName = "INVESTIMENTO_SEQ", allocationSize = 1)
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVESTIMENTO_SEQ_GEN")
    @Column(name = "ID_INVESTIMENTO", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "DATA_APLICACAO", nullable = false) // ajuste se preciso
    private LocalDate applicationDate;

    @NotNull @Digits(integer = 15, fraction = 2) @DecimalMin("0.01")
    @Column(name = "VALOR_APLICADO", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountApplied;

    @NotNull @Digits(integer = 4, fraction = 4) @DecimalMin("0.0001")
    @Column(name = "TAXA_ANUAL", nullable = false, precision = 5, scale = 2)
    private BigDecimal annualRate;

    @NotNull @Min(1)
    @Column(name = "PRAZO_MESES", nullable = false)
    private Integer months;

    @NotBlank @Size(max = 80)
    @Column(name = "PRODUTO", nullable = false, length = 80)
    private String product;

    public Investment(){}

    public Investment(Long id, LocalDate applicationDate, BigDecimal amountApplied, BigDecimal annualRate, Integer months, String product) {
        this.id=id; this.applicationDate=applicationDate; this.amountApplied=amountApplied; this.annualRate=annualRate; this.months=months; this.product=product;
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public LocalDate getApplicationDate(){ return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate){ this.applicationDate = applicationDate; }
    public BigDecimal getAmountApplied(){ return amountApplied; }
    public void setAmountApplied(BigDecimal amountApplied){ this.amountApplied = amountApplied; }
    public BigDecimal getAnnualRate(){ return annualRate; }
    public void setAnnualRate(BigDecimal annualRate){ this.annualRate = annualRate; }
    public Integer getMonths(){ return months; }
    public void setMonths(Integer months){ this.months = months; }
    public String getProduct(){ return product; }
    public void setProduct(String product){ this.product = product; }

    @Override public boolean equals(Object o){ if(this==o) return true; if(!(o instanceof Investment i)) return false; return Objects.equals(id,i.id); }
    @Override public int hashCode(){ return Objects.hash(id); }
}
