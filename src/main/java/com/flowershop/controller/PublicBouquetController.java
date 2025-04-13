package com.flowershop.controller;

import com.flowershop.entity.Bouquet;
import com.flowershop.service.BouquetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bouquets")
@CrossOrigin(origins = "http://localhost:3000")
public class PublicBouquetController {

    private final BouquetService service;

    public PublicBouquetController(BouquetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Bouquet> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bouquet> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
