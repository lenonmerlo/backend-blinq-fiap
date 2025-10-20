package com.fiap.fintech.service;

import com.fiap.fintech.domain.User;
import com.fiap.fintech.dto.ChangePasswordRequest;
import com.fiap.fintech.dto.UserCreateRequest;
import com.fiap.fintech.dto.UserDTO;
import com.fiap.fintech.dto.UserUpdateRequest;
import com.fiap.fintech.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UserDTO> list(int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return repo.findAll(pageable).map(this::toDTO);
    }

    public UserDTO get(Long id) {
        return toDTO(find(id));
    }

    public UserDTO create(UserCreateRequest req) {
        // unicidade
        if (repo.existsByUsername(req.username()))
            throw new IllegalStateException("Username já em uso");
        if (repo.existsByEmail(req.email()))
            throw new IllegalStateException("E-mail já em uso");

        // montar entidade
        var u = new User();
        u.setUsername(req.username());
        u.setEmail(req.email());
        u.setPasswordHash(passwordEncoder.encode(req.password()));
        // status/timestamps serão garantidos pelo @PrePersist da entidade
        u.setStatus((req.status() == null || req.status().isBlank()) ? "ATIVO" : req.status());

        return toDTO(repo.save(u));
    }

    public UserDTO update(Long id, UserUpdateRequest req) {
        var u = find(id);

        // checar unicidade somente se mudou
        if (!u.getUsername().equals(req.username()) && repo.existsByUsername(req.username()))
            throw new IllegalStateException("Username já em uso");

        if (!u.getEmail().equals(req.email()) && repo.existsByEmail(req.email()))
            throw new IllegalStateException("E-mail já em uso");

        u.setUsername(req.username());
        u.setEmail(req.email());
        u.setStatus((req.status() == null || req.status().isBlank()) ? u.getStatus() : req.status());

        return toDTO(repo.save(u));
    }

    public void changePassword(Long id, ChangePasswordRequest req) {
        var u = find(id);
        u.setPasswordHash(passwordEncoder.encode(req.newPassword()));
        repo.save(u);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new NoSuchElementException("USUARIO_NAO_ENCONTRADO");
        repo.deleteById(id);
    }

    // ===== helpers =====

    private User find(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("USUARIO_NAO_ENCONTRADO"));
    }

    private UserDTO toDTO(User u) {
        return new UserDTO(
                u.getId(),
                u.getUsername(),
                u.getStatus(),
                u.getCreatedAt(),
                u.getLastLogin()
        );
    }
}
