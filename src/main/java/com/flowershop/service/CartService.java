package com.flowershop.service;


import com.flowershop.DTO.CartItemDTO;
import com.flowershop.DTO.CartItemResponse;
import com.flowershop.entity.Bouquet;
import com.flowershop.entity.CartItem;
import com.flowershop.entity.User;
import com.flowershop.repository.BouquetRepository;
import com.flowershop.repository.CartItemRepository;
import com.flowershop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final BouquetRepository bouquetRepository;
    private final UserRepository userRepository;

    // Добавление товара в корзину
    public void addToCart(String username, CartItemDTO dto) {
        User user = userRepository.findByUserLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        Bouquet bouquet = bouquetRepository.findById(dto.getBouquetId())
                .orElseThrow(() -> new RuntimeException("Букет не найден"));

        CartItem item = cartItemRepository.findByUserAndBouquet(user, bouquet)
                .orElse(null);

        if (item != null) {
            item.setQuantity(item.getQuantity() + dto.getQuantity());
        } else {
            item = new CartItem(null, user, bouquet, dto.getQuantity());
        }

        cartItemRepository.save(item);
    }

    // Получение всех товаров в корзине с деталями
    public List<CartItemResponse> getCart(String username) {
        User user = userRepository.findByUserLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        List<CartItem> cartItems = cartItemRepository.findAllByUser(user);

        return cartItems.stream()
                .map(item -> new CartItemResponse(
                        item.getBouquet().getName(),
                        item.getQuantity(),
                        item.getTotalPrice(),
                        item.getBouquet().getImageUrl()
                ))
                .collect(Collectors.toList());
    }

    // Удаление товара из корзины
    public void removeFromCart(String username, Long cartItemId) {
        User user = userRepository.findByUserLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Элемент не найден"));

        if (item.getUser().equals(user)) {
            cartItemRepository.delete(item);
        }
    }
}



