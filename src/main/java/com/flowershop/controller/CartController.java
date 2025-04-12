//package com.flowershop.controller;
//
//import org.springframework.ui.Model;
//import com.flowershop.service.CartService;
//import com.flowershop.entity.Cart;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/api/cart")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @PostMapping("/add/{flowerId}")
//    @ResponseBody
//    public Cart addFlowerToCart(@PathVariable Long flowerId, @SessionAttribute("userId") Long userId) {
//        return cartService.addFlowerToCart(userId, flowerId);
//    }
//
//    @GetMapping
//    public String viewCart(Model model, @SessionAttribute("userId") Long userId) {
//        Cart cart = cartService.getCart(userId);
//        cart.calculateTotalPrice();
//        model.addAttribute("cartItems", cart.getItems());
//        model.addAttribute("totalPrice", cart.getTotalPrice());
//        return "cart";
//    }
//}
