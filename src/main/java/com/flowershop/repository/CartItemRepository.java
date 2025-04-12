package com.flowershop.repository;

import com.flowershop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Найти элемент корзины по id корзины и id цветка
    Optional<CartItem> findByCartIdAndFlowerId(Long cartId, Long flowerId);
    void deleteByCartId(Long cartId);
}