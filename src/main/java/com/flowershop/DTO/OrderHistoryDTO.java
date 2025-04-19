package com.flowershop.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryDTO {
    private Long id;
    private String status;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
}

