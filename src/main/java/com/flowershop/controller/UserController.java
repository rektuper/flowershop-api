package com.flowershop.controller;

import com.flowershop.DTO.UserProfileResponse;
import com.flowershop.entity.Role;
import com.flowershop.entity.User;
import com.flowershop.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUserLogin(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return ResponseEntity.ok(new UserProfileResponse(
                user.getId(),
                user.getUserLogin(),
                user.getRoles().stream().map(Role::getRoleName).toList()
        ));
    }
}
