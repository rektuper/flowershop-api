package com.flowershop.DTO;

import lombok.Data;

@Data
public class CartItemDTO {

    private Long id;
    public Long bouquetId;
    private Integer quantity;
}
