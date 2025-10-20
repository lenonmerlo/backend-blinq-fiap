package com.fiap.fintech.service;

import com.fiap.fintech.domain.User;
import com.fiap.fintech.dto.*;
import com.fiap.fintech.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public Page<UserDTO> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("id")
                        .ascending())).map(this::toDTO);
    }

    public UserDTO get(Long id) {
        return toDTO(find(id));
    }

    public UserDTO create(UserCreateRequest req) {
        if (repo.existsByUsername(req.username())) {
            throw new IllegalStateException("USERNAME_JA_EXISTE");
        }
        User u = new User();
        u.setUsername(req.username());
        u.setPasswordHash(sha256(req.password()));
        u.setStatus(req.status());
        u.setCreatedAt(LocalDateTime.now());
        u = repo.save(u);
        return toDTO(u);
    }

    public UserDTO update(Long id, UserUpdateRequest req) {
        User u = find(id);
        if (!u.getUsername().equals(req.username()) && repo.existsByUsername(req.username())) {
            throw new IllegalStateException("USERNAME_JA_EXISTE");
        }
        u.setUsername(req.username());
        u.setStatus(req.status());
        return toDTO(repo.save(u));
    }

    public void changePassword(Long id, ChangePasswordRequest req) {
        User u = find(id);
        u.setPasswordHash(sha256(req.newPassword()));
        repo.save(u);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("USUARIO_NAO_ENCONTRADO");
        repo.deleteById(id);
    }

    private User find(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("USUARIO_NAO_ENCONTRADO"));
    }

    private String sha256(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("HASH_ERROR", e);
        }
    }

    private UserDTO toDTO(User u) {
        return new UserDTO(u.getId(), u.getUsername(), u.getStatus(), u.getCreatedAt(), u.getLastLogin());
    }
}
