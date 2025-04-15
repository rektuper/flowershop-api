package com.flowershop.controller;

import com.flowershop.DTO.FavoriteBouquet;
import com.flowershop.entity.Bouquet;
import com.flowershop.entity.Favorite;
import com.flowershop.entity.User;
import com.flowershop.repository.BouquetRepository;
import com.flowershop.repository.FavoriteRepository;
import com.flowershop.repository.UserRepository;
import com.flowershop.service.FavoriteMapper;
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

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final BouquetRepository bouquetRepository;
    private final FavoriteMapper favoriteMapper;

    @PostMapping("/{bouquetId}")
    public ResponseEntity<?> addToFavorites(@PathVariable Long bouquetId, Principal principal) {
        User user = userRepository.findByUserLogin(principal.getName()).orElseThrow();
        Bouquet bouquet = bouquetRepository.findById(bouquetId).orElseThrow();

        if (favoriteRepository.findByUserAndBouquet(user, bouquet).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Уже добавлено в избранное");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setBouquet(bouquet);
        favoriteRepository.save(favorite);

        return ResponseEntity.ok("Добавлено в избранное");
    }

    @Transactional
    @DeleteMapping("/{bouquetId}")
    public ResponseEntity<?> removeFromFavorites(@PathVariable Long bouquetId, Principal principal) {
        User user = userRepository.findByUserLogin(principal.getName()).orElseThrow();
        Bouquet bouquet = bouquetRepository.findById(bouquetId).orElseThrow();

        favoriteRepository.deleteByUserAndBouquet(user, bouquet);

        return ResponseEntity.ok("Удалено из избранного");
    }

    @GetMapping
    public ResponseEntity<List<FavoriteBouquet>> getFavorites(Principal principal) {
        User user = userRepository.findByUserLogin(principal.getName()).orElseThrow();
        List<Favorite> favorites = favoriteRepository.findByUser(user);

        List<FavoriteBouquet> dtoList = favorites.stream()
                .map(fav -> favoriteMapper.toDto(fav.getBouquet()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }
}

