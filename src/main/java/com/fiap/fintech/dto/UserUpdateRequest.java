package com.fiap.fintech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @NotBlank @Size(min = 3, max = 80) String username,
        @NotBlank @Pattern(regexp = "ATIVO|INATIVO") String status
) {}