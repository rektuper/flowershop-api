package com.flowershop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Long id;
    private String bouquetName;
    private Integer quantity;
    private Double totalPrice;
    private String imageUrl;
}
