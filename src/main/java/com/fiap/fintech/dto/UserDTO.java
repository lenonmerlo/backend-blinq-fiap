package com.fiap.fintech.dto;

import java.time.LocalDateTime;

public record UserDTO(Long id,
                      String username,
                      String status,
                      LocalDateTime createAt,
                      LocalDateTime lastLogin) {
}
