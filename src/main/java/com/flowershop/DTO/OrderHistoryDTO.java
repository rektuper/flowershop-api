package com.flowershop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryDTO {
    private Long id;
    private String status;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
}

