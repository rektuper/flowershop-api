package com.flowershop.service;

import com.flowershop.DTO.FavoriteBouquet;
import com.flowershop.entity.Bouquet;
import org.springframework.stereotype.Service;

@Service
public class FavoriteMapper {

    public FavoriteBouquet toDto(Bouquet bouquet) {
        return new FavoriteBouquet(
                bouquet.getId(),
                bouquet.getName(),
                bouquet.getDescription(),
                bouquet.getPrice(),
                bouquet.getImageUrl()
        );
    }
}

