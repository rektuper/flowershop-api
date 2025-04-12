//package com.flowershop.controller;
//
//import com.flowershop.entity.User;
//import com.flowershop.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public User register(@RequestBody User user) {
//        return userService.registerUser(user.getUsername(), user.getPassword());
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody User user) {
//        try {
//            User existingUser = userService.findByUsername(user.getUsername());
//            if (new BCryptPasswordEncoder().matches(user.getPassword(), existingUser.getPassword())) {
//                return "Login successful";
//            }
//            return "Invalid credentials";
//        } catch (Exception e) {
//            return "Invalid credentials";
//        }
//    }
//}
