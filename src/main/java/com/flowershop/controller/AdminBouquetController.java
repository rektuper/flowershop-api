package com.flowershop.controller;

import com.flowershop.entity.Bouquet;
import com.flowershop.service.BouquetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/bouquets")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminBouquetController {

    private final BouquetService service;

    public AdminBouquetController(BouquetService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Bouquet> create(@Valid @RequestBody Bouquet bouquet) {
        Bouquet saved = service.save(bouquet);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bouquet> update(@PathVariable Long id, @Valid @RequestBody Bouquet bouquet) {
        Bouquet updated = service.update(id, bouquet);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}