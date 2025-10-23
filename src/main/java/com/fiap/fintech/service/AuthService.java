package com.fiap.fintech.service;

import com.fiap.fintech.dto.AuthRequest;
import com.fiap.fintech.dto.AuthResponse;
import com.fiap.fintech.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public AuthResponse login(AuthRequest req) {
        var user = repo.findByUsername(req.username())
                .orElseThrow(() -> new NoSuchElementException("USUARIO_NAO_ENCONTRADO"));
        if (!encoder.matches(req.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("CREDENCIAIS_INVALIDAS");
        }

        user.setLastLogin(LocalDateTime.now());
        repo.save(user);

        return new AuthResponse(user.getId(), user.getUsername(), null, "stub-" + UUID.randomUUID());
    }
}
