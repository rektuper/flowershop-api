//package com.flowershop.controller;
//
//import com.flowershop.entity.Order;
//import com.flowershop.model.CreateOrderRequest;
//import com.flowershop.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/api/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping("/create")
//    public Order createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
//        return orderService.placeOrder(createOrderRequest.getUserId(), createOrderRequest.getDeliveryAddress());
//    }
//
//    @GetMapping("/{id}")
//    public Order getOrder(@PathVariable Long id) {
//        return orderService.getOrderById(id);
//    }
//
//
//    @GetMapping("/user/{userId}")
//    public List<Order> getUserOrders(@PathVariable Long userId) {
//        return orderService.getOrdersByUserId(userId);
//    }
//}