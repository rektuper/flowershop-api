package com.flowershop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteBouquet {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
}
