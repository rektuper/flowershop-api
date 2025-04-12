package com.flowershop.service;

import com.flowershop.entity.Order;
import com.flowershop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Метод для создания заказа (уже добавлен)
    public Order placeOrder(Long userId, String deliveryAddress) {
        // Логика оформления заказа
        // ...
        return new Order(); // возвращаем созданный заказ
    }

    // Получение заказа по id
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new RuntimeException("Заказ не найден: id = " + id));
    }

    // Получение заказов по userId
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}