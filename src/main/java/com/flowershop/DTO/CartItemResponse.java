package com.flowershop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Long id;
    private String bouquetName;
    private Integer quantity;
    private BigDecimal totalPrice;
    private String imageUrl;
}
