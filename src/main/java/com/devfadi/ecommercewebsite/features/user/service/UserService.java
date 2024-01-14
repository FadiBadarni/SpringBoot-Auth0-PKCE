package com.devfadi.ecommercewebsite.features.user.service;

import com.devfadi.ecommercewebsite.exception.InvalidTokenException;
import com.devfadi.ecommercewebsite.features.user.controller.Auth0Client;
import com.devfadi.ecommercewebsite.features.user.dto.UserDTO;
import com.devfadi.ecommercewebsite.features.user.entity.User;
import com.devfadi.ecommercewebsite.features.user.repository.UserRepository;
import com.devfadi.ecommercewebsite.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService
{

    private final UserRepository userRepository;
    private final Auth0Client auth0Client;
    private final UserMapper userMapper;


    public UserDTO registerOrUpdateUser(HttpServletRequest request) {
        String accessToken = extractTokenFromHeader(request);
        if (accessToken == null) {
            throw new InvalidTokenException("Invalid or missing token", null);
        }

        UserDTO userDTO = auth0Client.getUserInfo(accessToken);
        User user = userMapper.fromDTO(userDTO);

        Optional<User> existingUser = userRepository.findByAuth0Id(user.getAuth0Id());

        if (existingUser.isPresent()) {
            User existing = existingUser.get();
            updateExistingUser(existing, user);
            userRepository.save(existing);
            return userMapper.toDTO(existing);
        } else {
            userRepository.save(user);
            return userDTO;
        }
    }

    private void updateExistingUser(User existing, User updated) {
        existing.setEmail(updated.getEmail());
        existing.setFullName(updated.getFullName());
        existing.setProfilePicture(updated.getProfilePicture());
        existing.setEmailVerified(updated.getEmailVerified());
        existing.setRoles(updated.getRoles());
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
