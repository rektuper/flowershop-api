package com.flowershop.repository;

import com.flowershop.entity.CartItem;
import com.flowershop.entity.User;
import com.flowershop.entity.Bouquet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUser(User user);

    Optional<CartItem> findByUserAndBouquet(User user, Bouquet bouquet);
}
