package com.fiap.fintech.controller;

import com.fiap.fintech.dto.AuthRequest;
import com.fiap.fintech.dto.AuthResponse;
import com.fiap.fintech.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid
            @RequestBody
            AuthRequest req
    ) {
        return ResponseEntity.ok(service.login(req));
    }
}
