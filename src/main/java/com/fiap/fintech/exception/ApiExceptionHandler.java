package com.fiap.fintech.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {

    // 400 - Bad Request (validação bean validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst().orElse("Dados inválidos");
        return body(400, "Bad Request", msg, req.getRequestURI());
    }

    // 400 - Body inválido / JSON malformado
    @ExceptionHandler({ HttpMessageNotReadableException.class, MissingServletRequestParameterException.class })
    public ResponseEntity<Map<String,Object>> badRequest(Exception ex, HttpServletRequest req) {
        return body(400, "Bad Request", "Requisição inválida ou corpo malformado.", req.getRequestURI());
    }

    // 404 - Not Found
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String,Object>> notFound(NoSuchElementException ex, HttpServletRequest req) {
        return body(404, "Not Found", ex.getMessage(), req.getRequestURI());
    }

    // 405 - Method Not Allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String,Object>> methodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest req) {
        return body(405, "Method Not Allowed", "Método não suportado para este recurso.", req.getRequestURI());
    }

    // 409 - Conflict (violação de FK/único, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,Object>> conflict(DataIntegrityViolationException ex, HttpServletRequest req) {
        return body(409, "Conflict", "Violação de integridade de dados.", req.getRequestURI());
    }

    // 415 - Unsupported Media Type
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String,Object>> unsupported(HttpMediaTypeNotSupportedException ex, HttpServletRequest req) {
        return body(415, "Unsupported Media Type", "Content-Type não suportado.", req.getRequestURI());
    }

    // 422 - Unprocessable Entity (regras de negócio)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> unprocessable(IllegalArgumentException ex, HttpServletRequest req) {
        return body(422, "Unprocessable Entity", ex.getMessage(), req.getRequestURI());
    }

    // 500 - Fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> generic(Exception ex, HttpServletRequest req) {
        return body(500, "Internal Server Error", "Erro inesperado.", req.getRequestURI());
    }

    private ResponseEntity<Map<String,Object>> body(int status, String error, String message, String path) {
        Map<String,Object> m = new LinkedHashMap<>();
        m.put("timestamp", Instant.now().toString());
        m.put("status", status);
        m.put("error", error);
        m.put("message", message);
        m.put("path", path);
        return ResponseEntity.status(status).body(m);
    }
}
