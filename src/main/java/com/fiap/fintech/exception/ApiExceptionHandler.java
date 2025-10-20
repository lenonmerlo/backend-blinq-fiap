package com.fiap.fintech.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {

    // 400 - Bean Validation (body válido mas campos inválidos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst().orElse("Dados inválidos");
        return of(HttpStatus.BAD_REQUEST, msg, req.getRequestURI());
    }

    // 400 - Body inválido / JSON malformado / parâmetro obrigatório ausente
    @ExceptionHandler({ HttpMessageNotReadableException.class, MissingServletRequestParameterException.class })
    public ResponseEntity<ApiError> badRequest(Exception ex, HttpServletRequest req) {
        return of(HttpStatus.BAD_REQUEST, "Requisição inválida ou corpo malformado.", req.getRequestURI());
    }

    // 404 - Not Found
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleNotFound(NoSuchElementException ex, HttpServletRequest req) {
        return of(HttpStatus.NOT_FOUND, ex.getMessage(), req.getRequestURI());
    }

    // 405 - Method Not Allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> methodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest req) {
        return of(HttpStatus.METHOD_NOT_ALLOWED, "Método não suportado para este recurso.", req.getRequestURI());
    }

    // 409 - Conflict (constraints de banco / unique / FK etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> conflict(DataIntegrityViolationException ex, HttpServletRequest req) {
        return of(HttpStatus.CONFLICT, "Violação de integridade de dados.", req.getRequestURI());
    }

    // 409 - Conflict (regras de negócio, ex.: username já existe)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handleConflict(IllegalStateException ex, HttpServletRequest req) {
        return of(HttpStatus.CONFLICT, ex.getMessage(), req.getRequestURI());
    }

    // 400 - Bad Request (regras simples de domínio/validação manual)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex, HttpServletRequest req) {
        return of(HttpStatus.BAD_REQUEST, ex.getMessage(), req.getRequestURI());
    }

    // 415 - Unsupported Media Type
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiError> unsupported(HttpMediaTypeNotSupportedException ex, HttpServletRequest req) {
        return of(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Content-Type não suportado.", req.getRequestURI());
    }

    // 500 - Fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> generic(Exception ex, HttpServletRequest req) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado.", req.getRequestURI());
    }

    // Helper centralizado
    private ResponseEntity<ApiError> of(HttpStatus status, String message, String path) {
        ApiError body = new ApiError(
                Instant.now().toString(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
        return ResponseEntity.status(status).body(body);
    }
}
