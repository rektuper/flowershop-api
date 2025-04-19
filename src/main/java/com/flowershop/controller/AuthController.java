package com.flowershop.controller;

import com.flowershop.DTO.AuthRequest;
import com.flowershop.DTO.RegisterRequest;
import com.flowershop.DTO.AuthResponse;
import com.flowershop.entity.Role;
import com.flowershop.entity.User;
import com.flowershop.repository.RoleRepository;
import com.flowershop.repository.UserRepository;
import com.flowershop.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserLogin(), request.getUserPassword())
            );

            User user = userRepository.findByUserLogin(request.getUserLogin())
                    .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new AuthResponse("Неверный логин или пароль"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        System.out.println("==> userLogin = " + request.getUserLogin());
        System.out.println("==> userPassword = " + request.getUserPassword());
        if (userRepository.findByUserLogin(request.getUserLogin()).isPresent()) {
            return ResponseEntity.badRequest().body(new AuthResponse("Пользователь с таким именем уже существует"));
        }

        Role roleUser = roleRepository.findByRoleName("ROLE_USER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRoleName("ROLE_USER");
                    return roleRepository.save(newRole);
                });

        User newUser = new User();
        newUser.setUserLogin(request.getUserLogin());
        newUser.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        newUser.setRoles(Set.of(roleUser));
        userRepository.save(newUser);

        String token = jwtUtil.generateToken(newUser);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
