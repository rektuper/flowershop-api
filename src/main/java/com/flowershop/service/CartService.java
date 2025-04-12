package com.flowershop.service;

import com.flowershop.entity.Cart;
import com.flowershop.entity.CartItem;
import com.flowershop.entity.Flower;
import com.flowershop.entity.User;
import com.flowershop.repository.CartRepository;
import com.flowershop.repository.CartItemRepository;
import com.flowershop.repository.FlowerRepository;
import com.flowershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private UserRepository userRepository;

    // Добавление цветка в корзину
    public Cart addFlowerToCart(Long userId, Long flowerId) {
        // Находим пользователя по ID
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }

        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            cart = new Cart(user.get());
            cartRepository.save(cart);
        }

        // Находим цветок по ID
        Optional<Flower> flower = flowerRepository.findById(flowerId);
        if (!flower.isPresent()) {
            throw new RuntimeException("Flower not found");
        }

        // Проверяем, есть ли уже этот цветок в корзине
        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndFlowerId(cart.getId(), flower.get().getId());

        if (existingItem.isPresent()) {
            // Если цветок уже есть в корзине, увеличиваем количество
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
            cartItemRepository.save(item);
        } else {
            // Если цветок не найден в корзине, добавляем новый элемент
            CartItem cartItem = new CartItem(cart, flower.get(), 1);
            cartItemRepository.save(cartItem);
        }

        // Обновляем общую цену корзины
        cart.calculateTotalPrice();
        cartRepository.save(cart);

        return cart;
    }

    // Получить корзину пользователя
    public Cart getCart(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        return cart.orElseThrow(() -> new RuntimeException("Cart not found for user"));
    }

    // Очистить корзину пользователя
    public void clearCart(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (cart.isPresent()) {
            // Если корзина существует, удаляем все элементы и саму корзину
            cartItemRepository.deleteByCartId(cart.get().getId());
            cartRepository.delete(cart.get());
        }
    }
}

