package com.fiap.fintech.controller;

import com.fiap.fintech.domain.Investment;
import com.fiap.fintech.service.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService service;
    public InvestmentController(InvestmentService service){ this.service = service; }

    @GetMapping
    public Page<Investment> list(@RequestParam(defaultValue="0") int page,
                                 @RequestParam(defaultValue="10") int size){
        return service.list(page,size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> get(@PathVariable Long id){
        return ResponseEntity.ok(service.getOrThrow(id));
    }

    @PostMapping
    public ResponseEntity<Investment> create(@Valid @RequestBody Investment i, UriComponentsBuilder uri){
        Investment saved = service.create(i);
        URI location = uri.path("/api/investments/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> update(@PathVariable Long id, @Valid @RequestBody Investment i){
        return ResponseEntity.ok(service.update(id, i));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
