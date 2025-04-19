package com.flowershop.controller;

import com.flowershop.entity.Bouquet;
import com.flowershop.service.BouquetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bouquets")
public class BouquetController {

    private final BouquetService service;

    public BouquetController(BouquetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Bouquet> getAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Bouquet> create(
            @Valid @RequestBody Bouquet bouquet,
            @AuthenticationPrincipal UserDetails userDetails) {

        Bouquet saved = service.save(bouquet);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bouquet> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bouquet> update(
            @PathVariable Long id,
            @Valid @RequestBody Bouquet bouquet,
            @AuthenticationPrincipal UserDetails userDetails) {

        Bouquet updated = service.update(id, bouquet);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}