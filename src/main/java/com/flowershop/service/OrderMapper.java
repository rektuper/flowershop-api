package com.flowershop.service;

import com.flowershop.DTO.OrderDTO;
import com.flowershop.DTO.OrderItemDTO;
import com.flowershop.entity.Order;
import com.flowershop.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public OrderDTO mapToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());

        List<OrderItemDTO> orderItemsDTO = order.getOrderItems().stream()
                .map(this::mapToOrderItemDTO)
                .collect(Collectors.toList());
        orderDTO.setOrderItems(orderItemsDTO);

        return orderDTO;
    }

    private OrderItemDTO mapToOrderItemDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setBouquetName(orderItem.getBouquet().getName());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPriceAtPurchase(orderItem.getPriceAtPurchase());
        return dto;
    }
}
