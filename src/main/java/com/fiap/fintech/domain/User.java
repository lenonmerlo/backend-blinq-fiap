package com.fiap.fintech.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "USUARIO")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ_GEN")
    @SequenceGenerator(name = "USUARIO_SEQ_GEN", sequenceName = "USUARIO_SEQ", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    private String username;

    @NotBlank @Email
    @Size(max = 100)
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank
    @Column(name = "SENHA_HASH", nullable = false, length = 100)
    private String passwordHash;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status; // "ATIVO" | "INATIVO"

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ULTIMO_LOGIN", nullable = false)
    private LocalDateTime lastLogin;

    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (lastLogin == null) lastLogin = createdAt;
        if (status == null || status.isBlank()) status = "ATIVO";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}
