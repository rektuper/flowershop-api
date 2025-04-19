package com.flowershop.DTO;

import com.flowershop.model.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private OrderStatus status;
}
