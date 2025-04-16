package com.flowershop.repository;

import com.flowershop.entity.Order;
import com.flowershop.entity.User;
import com.flowershop.model.OrderStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByUserAndStatus(User user, OrderStatus status);

    List<Order> findByUser(User user, Sort sort);

    List<Order> findByUserAndStatus(User user, OrderStatus status, Sort sort);
}