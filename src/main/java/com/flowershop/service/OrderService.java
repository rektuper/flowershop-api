package com.flowershop.service;

import com.flowershop.DTO.OrderHistoryDTO;
import com.flowershop.entity.CartItem;
import com.flowershop.entity.Order;
import com.flowershop.entity.OrderItem;
import com.flowershop.entity.User;
import com.flowershop.model.OrderStatus;
import com.flowershop.repository.CartItemRepository;
import com.flowershop.repository.OrderRepository;
import com.flowershop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public Order placeOrder(User user) {
        List<CartItem> cartItems = cartItemRepository.findAllByUser(user);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Корзина пуста");
        }

        // Создаём новый заказ
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.NEW);
        order.setOrderDate(LocalDateTime.now());

        // Считаем цену и создаём позиции в заказе
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBouquet(cartItem.getBouquet());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(cartItem.getBouquet().getPrice());
            orderItem.setOrder(order); // Устанавливаем связь с заказом

            orderItems.add(orderItem);

            BigDecimal itemTotal = cartItem.getBouquet().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);
        }

        order.setOrderItems(orderItems); // Устанавливаем все товары
        order.setTotalPrice(totalPrice); // Устанавливаем общую сумму

        // Сохраняем заказ
        Order savedOrder = orderRepository.save(order);

        // Очищаем корзину после оформления
        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    public List<OrderHistoryDTO> getAllOrderHistoriesSorted(String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by("orderDate").ascending()
                : Sort.by("orderDate").descending();

        List<Order> orders = orderRepository.findAll(sort);

        return orders.stream()
                .map(order -> new OrderHistoryDTO(
                        order.getId(),
                        order.getStatus().name(),
                        order.getOrderDate(),
                        order.getTotalPrice()
                ))
                .toList();
    }

    public void cancelOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getStatus() == OrderStatus.NEW || order.getStatus() == OrderStatus.PAID) {
                order.setStatus(OrderStatus.CANCELED);
                orderRepository.save(order);
            } else {
                throw new IllegalStateException("Этот заказ уже нельзя отменить.");
            }
        } else {
            throw new IllegalArgumentException("Заказ с таким ID не найден.");
        }
    }


    public List<OrderHistoryDTO> getOrderHistoryForUser(User user) {
        return orderRepository.findByUser(user).stream()
                .map(order -> new OrderHistoryDTO(
                        order.getId(),
                        order.getStatus().name(),
                        order.getOrderDate(),
                        order.getTotalPrice()
                ))
                .toList();
    }

    public List<OrderHistoryDTO> getOrderHistoryForUserByStatus(User user, OrderStatus status) {
        return orderRepository.findByUserAndStatus(user, status)
                .stream()
                .map(order -> new OrderHistoryDTO(
                        order.getId(),
                        order.getStatus().name(),
                        order.getOrderDate(),
                        order.getTotalPrice()
                ))
                .toList();
    }

    public List<OrderHistoryDTO> getOrderHistorySorted(User user, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by("orderDate").ascending()
                : Sort.by("orderDate").descending();

        List<Order> orders = orderRepository.findByUser(user, sort);

        return orders.stream()
                .map(order -> new OrderHistoryDTO(
                        order.getId(),
                        order.getStatus().name(),
                        order.getOrderDate(),
                        order.getTotalPrice()
                ))
                .toList();
    }

    public List<OrderHistoryDTO> getOrderHistoryByStatus(User user, OrderStatus status) {
        List<Order> orders = orderRepository.findByUserAndStatus(user, status);

        return orders.stream()
                .map(order -> new OrderHistoryDTO(
                        order.getId(),
                        order.getStatus().name(),
                        order.getOrderDate(),
                        order.getTotalPrice()
                ))
                .toList();
    }

    public List<OrderHistoryDTO> getOrderHistoryFilteredAndSorted(User user, OrderStatus status, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by("orderDate").ascending()
                : Sort.by("orderDate").descending();

        List<Order> orders = orderRepository.findByUserAndStatus(user, status, sort);

        return orders.stream()
                .map(order -> new OrderHistoryDTO(
                        order.getId(),
                        order.getStatus().name(),
                        order.getOrderDate(),
                        order.getTotalPrice()
                ))
                .toList();
    }

    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Заказ не найден"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersForUserByStatus(User user, OrderStatus status) {
        return orderRepository.findByUserAndStatus(user, status);
    }

    public List<Order> getOrdersForUser(User user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}