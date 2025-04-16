package com.flowershop.service;

import com.flowershop.DTO.FavoriteBouquet;
import com.flowershop.entity.Bouquet;
import com.flowershop.entity.Favorite;
import com.flowershop.entity.User;
import com.flowershop.repository.BouquetRepository;
import com.flowershop.repository.FavoriteRepository;
import com.flowershop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final BouquetRepository bouquetRepository;
    private final FavoriteMapper favoriteMapper;

    public String addToFavorites(Long bouquetId, String username) {
        User user = userRepository.findByUserLogin(username).orElseThrow();
        Bouquet bouquet = bouquetRepository.findById(bouquetId).orElseThrow();

        Optional<Favorite> existingFavorite = favoriteRepository.findByUserAndBouquet(user, bouquet);
        if (existingFavorite.isEmpty()) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setBouquet(bouquet);
            favoriteRepository.save(favorite);
            return "Добавлено в избранное";
        }

        return "Уже добавлено в избранное";
    }

    @Transactional
    public String removeFromFavorites(Long bouquetId, String username) {
        User user = userRepository.findByUserLogin(username).orElseThrow();
        Bouquet bouquet = bouquetRepository.findById(bouquetId).orElseThrow();
        favoriteRepository.deleteByUserAndBouquet(user, bouquet);
        return "Удалено из избранного";
    }

    public List<FavoriteBouquet> getFavorites(String username) {
        User user = userRepository.findByUserLogin(username).orElseThrow();
        return favoriteRepository.findByUser(user).stream()
                .map(fav -> favoriteMapper.toDto(fav.getBouquet()))
                .toList();
    }
}

