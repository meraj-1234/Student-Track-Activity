package com.studenttrack.controller;

import com.studenttrack.dto.AuthResponse;
import com.studenttrack.dto.LoginRequest;
import com.studenttrack.dto.RegisterRequest;
import com.studenttrack.model.User;
import com.studenttrack.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        logger.info("Register attempt for email: {}", request.getEmail());
        
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            logger.warn("Register failed: Email {} already in use", request.getEmail());
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
        logger.info("User registered successfully: {}", request.getEmail());

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.info("Login attempt for email: {}", request.getEmail());
        
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        
        if (userOptional.isEmpty()) {
            logger.warn("Login failed: User with email {} not found", request.getEmail());
            return ResponseEntity.status(401).body("Error: Invalid email or password!");
        }

        User user = userOptional.get();
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            logger.warn("Login failed: Incorrect password for email {}", request.getEmail());
            return ResponseEntity.status(401).body("Error: Invalid email or password!");
        }

        logger.info("Login successful for email: {}", request.getEmail());

        return ResponseEntity.ok(AuthResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build());
    }
}
