package com.devfadi.ecommercewebsite.features.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {


    @PostMapping("/callback")
    public ResponseEntity<?> callback(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String accessToken = extractAccessToken(authorizationHeader);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            log.error("Error in callback: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    private String extractAccessToken(String authorizationHeader) {
        // Assuming the header format is "Bearer <token>"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new IllegalArgumentException("Invalid Authorization header");
    }

}