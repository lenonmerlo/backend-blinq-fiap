package com.fiap.fintech.exception;

public record ApiError(
        String timestamp,
        int status,
        String error,
        String message,
        String path
) {}
