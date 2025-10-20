package com.fiap.fintech.controller;

import com.fiap.fintech.domain.Income;
import com.fiap.fintech.service.IncomeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    private final IncomeService service;

    public IncomeController(IncomeService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Income> list(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        return service.list(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrThrow(id));
    }

    @PostMapping
    public ResponseEntity<Income> create(@Valid @RequestBody Income i, UriComponentsBuilder uri) {
        Income saved = service.create(i);
        URI location = uri.path("/api/incomes/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> update(@PathVariable Long id, @Valid @RequestBody Income i) {
        return ResponseEntity.ok(service.update(id, i));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}