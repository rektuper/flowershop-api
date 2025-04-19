package com.flowershop.controller;

import com.flowershop.DTO.OrderHistoryDTO;
import com.flowershop.DTO.UpdateOrderStatusRequest;
import com.flowershop.entity.Order;
import com.flowershop.model.OrderStatus;
import com.flowershop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping("/history")
    public ResponseEntity<List<OrderHistoryDTO>> getOrderHistory(
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(defaultValue = "desc") String sort
    ) {
        if (status != null) {
            List<Order> orders = orderService.getAllOrders().stream()
                    .filter(order -> order.getStatus() == status)
                    .toList();

            List<OrderHistoryDTO> dtoList = orders.stream()
                    .map(order -> new OrderHistoryDTO(
                            order.getId(),
                            order.getStatus().name(),
                            order.getOrderDate(),
                            order.getTotalPrice()
                    ))
                    .toList();

            return ResponseEntity.ok(dtoList);
        } else {
            return ResponseEntity.ok(orderService.getAllOrderHistoriesSorted(sort));
        }
    }

    @GetMapping("/history/filter")
    public ResponseEntity<List<OrderHistoryDTO>> getOrderHistoryByStatus(@RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.getOrderHistoryByStatus(null, status));
    }

    @GetMapping("/history/sorted")
    public ResponseEntity<List<OrderHistoryDTO>> getSortedOrderHistory(
            @RequestParam(defaultValue = "desc") String sort
    ) {
        return ResponseEntity.ok(orderService.getOrderHistorySorted(null, sort));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest request
    ) {
        Order updated = orderService.updateOrderStatus(orderId, request.getStatus());
        return ResponseEntity.ok(updated);
    }
}
