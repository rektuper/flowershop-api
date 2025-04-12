//package com.flowershop.service;
//
//import com.flowershop.entity.User;
//import com.flowershop.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public User registerUser(String username, String password) {
//        if (userRepository.findByUsername(username).isPresent()) {
//            throw new IllegalArgumentException("User already exists");
//        }
//
//        String hashedPassword = passwordEncoder.encode(password);
//        User user = new User(username, hashedPassword);
//        return userRepository.save(user);
//    }
//
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
//    }
//}
