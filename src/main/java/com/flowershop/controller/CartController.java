package com.flowershop.controller;

import com.flowershop.DTO.*;
import com.flowershop.DTO.CartItemDTO;
import com.flowershop.entity.CartItem;
import com.flowershop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Добавление товара в корзину
    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItemDTO dto, Principal principal) {
        CartItem item = cartService.addToCart(principal.getName(), dto);
        return ResponseEntity.ok(item);
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

    // Обновление кол-ва предмета
    @PostMapping("/update")
    public ResponseEntity<Void> updateQuantity(@RequestBody CartItemDTO dto, Principal principal){
        cartService.updateQuantity(principal.getName(), dto.getBouquetId(), dto.getQuantity());
        return ResponseEntity.ok().build();
    }
}
