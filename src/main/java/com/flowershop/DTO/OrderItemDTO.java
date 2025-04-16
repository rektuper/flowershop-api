package com.flowershop.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long id;
    private String bouquetName;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
}
