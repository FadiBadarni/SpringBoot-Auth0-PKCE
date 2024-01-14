package com.devfadi.ecommercewebsite.mapper;

import com.devfadi.ecommercewebsite.features.user.dto.UserDTO;
import com.devfadi.ecommercewebsite.features.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper
{

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity
     * @return the UserDTO
     */
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder().auth0Id(user.getAuth0Id()).email(user.getEmail()).fullName(user.getFullName())
                      .profilePicture(user.getProfilePicture()).emailVerified(user.getEmailVerified()).build();
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the UserDTO
     * @return the User entity
     */
    public User fromDTO(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return User.builder().auth0Id(userDTO.getAuth0Id()).email(userDTO.getEmail()).fullName(userDTO.getFullName())
                   .profilePicture(userDTO.getProfilePicture()).emailVerified(userDTO.getEmailVerified()).build();
    }

}
