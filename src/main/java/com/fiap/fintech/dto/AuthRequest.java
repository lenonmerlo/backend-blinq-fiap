package com.fiap.fintech.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(String username, String password) {}



