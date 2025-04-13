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

    // Доступен всем (например, на главной странице магазина)
    @GetMapping
    public List<Bouquet> getAll() {
        return service.findAll();
    }

    // Только для аутентифицированных пользователей
    @PostMapping
    public ResponseEntity<Bouquet> create(
            @Valid @RequestBody Bouquet bouquet,
            @AuthenticationPrincipal UserDetails userDetails) {

        // можно проверить роли, если нужно
        Bouquet saved = service.save(bouquet);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bouquet> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // Только для аутентифицированных пользователей
    @PutMapping("/{id}")
    public ResponseEntity<Bouquet> update(
            @PathVariable Long id,
            @Valid @RequestBody Bouquet bouquet,
            @AuthenticationPrincipal UserDetails userDetails) {

        Bouquet updated = service.update(id, bouquet);
        return ResponseEntity.ok(updated);
    }

    // Только для аутентифицированных пользователей
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
