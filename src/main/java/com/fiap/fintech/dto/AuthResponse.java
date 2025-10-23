package com.fiap.fintech.dto;

public record AuthResponse(
        Long userId,
        String username,
        String displayName,
        String token
) {
}
