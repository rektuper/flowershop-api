package com.flowershop.controller;

import com.flowershop.DTO.OrderDTO;
import com.flowershop.entity.Order;
import com.flowershop.entity.User;
import com.flowershop.service.OrderMapper;
import com.flowershop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@AuthenticationPrincipal User user) {
        Order order = orderService.placeOrder(user);
        OrderDTO orderDTO = orderMapper.mapToDTO(order);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getUserOrders(@AuthenticationPrincipal User user) {
        List<Order> orders = orderService.getOrdersForUser(user);
        List<OrderDTO> orderDTOs = orders.stream()
                .map(orderMapper::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(order -> ResponseEntity.ok(orderMapper.mapToDTO(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        try {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("Заказ успешно отменен.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }
}
