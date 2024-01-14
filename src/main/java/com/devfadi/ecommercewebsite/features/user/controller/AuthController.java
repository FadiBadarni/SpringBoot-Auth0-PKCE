package com.devfadi.ecommercewebsite.features.user.controller;

import com.devfadi.ecommercewebsite.features.user.dto.UserDTO;
import com.devfadi.ecommercewebsite.features.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class AuthController
{

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerOrUpdateUser(HttpServletRequest request) {
        UserDTO user = userService.registerOrUpdateUser(request);
        return ResponseEntity.ok(user);
    }
}
