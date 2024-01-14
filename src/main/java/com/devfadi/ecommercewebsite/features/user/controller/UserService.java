package com.devfadi.ecommercewebsite.features.user.controller;

import com.devfadi.ecommercewebsite.features.user.dto.UserDTO;
import com.devfadi.ecommercewebsite.features.user.entity.User;
import com.devfadi.ecommercewebsite.features.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Auth0Client auth0Client;
    public UserDTO registerOrUpdateUser(String accessToken) {
        UserDTO userDTO = auth0Client.getUserInfo(accessToken);
        String auth0Id = userDTO.getAuth0Id();

        // Check if user already exists in the database
        Optional<User> existingUser = userRepository.findByAuth0Id(auth0Id);

        User user;
        // Update existing user's information
        user = existingUser.orElseGet(User::new);

        // Set fields from userDTO to user
        user.setAuth0Id(auth0Id);
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setProfilePicture(userDTO.getPicture());
        user.setEmailVerified(userDTO.getEmailVerified());
        user.setRoles(userDTO.getRoles());

        userRepository.save(user);

        return userDTO;
    }
}
