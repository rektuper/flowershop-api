package com.flowershop.controller;

import com.flowershop.DTO.*;
import com.flowershop.DTO.CartItemDTO;
import com.flowershop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Добавление товара в корзину
    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestBody CartItemDTO dto, Principal principal) {
        cartService.addToCart(principal.getName(), dto);
        return ResponseEntity.ok().build();
    }

    // Получение всех товаров в корзине
    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getCart(Principal principal) {
        List<CartItemResponse> cart = cartService.getCart(principal.getName());
        return ResponseEntity.ok(cart);
    }

    // Удаление товара из корзины
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id, Principal principal) {
        cartService.removeFromCart(principal.getName(), id);
        return ResponseEntity.ok().build();
    }
}
