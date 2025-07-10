package com.example.LibraryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagementSystem.model.User;
import com.example.LibraryManagementSystem.response.ApiResponse;
import com.example.LibraryManagementSystem.response.JwtResponse;
import com.example.LibraryManagementSystem.security.JwtUtil;
import com.example.LibraryManagementSystem.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
 @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse("Username already exists!", false, user));
        }
        
        userService.saveUser(user);
        return ResponseEntity.ok(new ApiResponse("User registered successfully!", true, user));
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(token, user.getUsername()));
        }
        
        return ResponseEntity.badRequest()
            .body(new ApiResponse("Invalid username or password!", false, user));
    }
}

