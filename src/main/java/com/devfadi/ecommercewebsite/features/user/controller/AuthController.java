package com.devfadi.ecommercewebsite.features.user.controller;

import com.devfadi.ecommercewebsite.features.user.dto.UserDTO;
import com.devfadi.ecommercewebsite.features.user.entity.User;
import com.devfadi.ecommercewebsite.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            User registeredUser = userService.registerUser(userDTO);
            UserDTO registeredUserDTO = userMapper.toDTO(registeredUser);
            return ResponseEntity.ok(registeredUserDTO);
        } catch (Exception e) {
            log.error("Error in user registration: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}