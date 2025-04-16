package com.flowershop.controller;

import com.flowershop.DTO.FavoriteBouquet;
import com.flowershop.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{bouquetId}")
    public ResponseEntity<?> addToFavorites(@PathVariable Long bouquetId, Principal principal) {
        try {
            String message = favoriteService.addToFavorites(bouquetId, principal.getName());
            return ResponseEntity.ok(message);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Transactional
    @DeleteMapping("/{bouquetId}")
    public ResponseEntity<?> removeFromFavorites(@PathVariable Long bouquetId, Principal principal) {
        String message = favoriteService.removeFromFavorites(bouquetId, principal.getName());
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<FavoriteBouquet>> getFavorites(Principal principal) {
        List<FavoriteBouquet> favorites = favoriteService.getFavorites(principal.getName());
        return ResponseEntity.ok(favorites);
    }
}


