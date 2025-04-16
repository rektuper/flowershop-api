package com.flowershop.DTO;

import com.flowershop.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {
    private OrderStatus status;
}
