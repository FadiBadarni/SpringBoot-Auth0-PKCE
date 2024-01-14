package com.devfadi.ecommercewebsite.features.user.controller;

import com.devfadi.ecommercewebsite.features.user.dto.UserDTO;
import com.devfadi.ecommercewebsite.features.user.entity.User;
import com.devfadi.ecommercewebsite.features.user.repository.UserRepository;
import com.devfadi.ecommercewebsite.mapper.UserMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User registerUser(UserDTO userDTO) {
        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists with email: " + userDTO.getEmail());
        }

        User newUser = userMapper.fromDTO(userDTO);

        return userRepository.save(newUser);
    }
}
