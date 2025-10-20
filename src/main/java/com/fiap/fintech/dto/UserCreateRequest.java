package com.fiap.fintech.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank @Size(min = 3, max = 80) String username,
        @NotBlank @Size(min = 6, max = 64) String password,
        @NotBlank @Email @Size(max = 120) String email,
        // status pode vir nulo; o service usa "ATIVO" por padrão
        @Pattern(regexp = "ATIVO|INATIVO") String status
) {}
