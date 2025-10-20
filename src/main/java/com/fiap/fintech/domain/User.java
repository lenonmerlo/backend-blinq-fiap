package com.fiap.fintech.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "USUARIO")
@SequenceGenerator(name = "USUARIO_SEQ_GEN", sequenceName = "USUARIO_SEQ", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ_GEN")
    @Column(name = "ID_USUARIO", nullable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false, length = 80, unique = true)
    private String username;

    @Column(name = "SENHA_HASH", nullable = false, length = 128)
    private String passwordHash;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ULTIMO_LOGIN")
    private LocalDateTime lastLogin;

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
