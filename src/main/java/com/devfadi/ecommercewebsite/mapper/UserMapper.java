package com.devfadi.ecommercewebsite.mapper;

import com.devfadi.ecommercewebsite.features.user.dto.UserDTO;
import com.devfadi.ecommercewebsite.features.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

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

        return UserDTO.builder()
                      .auth0Id(user.getAuth0Id())
                      .email(user.getEmail())
                      .fullName(user.getFullName())
                      .profilePicture(user.getProfilePicture())
                      .emailVerified(user.getEmailVerified())
                      .build();
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

        return User.builder()
                   .auth0Id(userDTO.getAuth0Id())
                   .email(userDTO.getEmail())
                   .fullName(userDTO.getFullName())
                   .profilePicture(userDTO.getProfilePicture())
                   .emailVerified(userDTO.getEmailVerified())
                   .build();
    }

    /**
     * Converts a collection of User entities to a collection of UserDTOs.
     *
     * @param users the collection of User entities
     * @return the collection of UserDTOs
     */
    public Set<UserDTO> toDTOCollection(Collection<User> users) {
        if (users == null) {
            return Collections.emptySet();
        }

        return users.stream()
                    .map(this::toDTO)
                    .collect(Collectors.toSet());
    }

    /**
     * Converts a collection of UserDTOs to a collection of User entities.
     *
     * @param userDTOs the collection of UserDTOs
     * @return the collection of User entities
     */
    public Set<User> fromDTOCollection(Collection<UserDTO> userDTOs) {
        if (userDTOs == null) {
            return Collections.emptySet();
        }

        return userDTOs.stream()
                       .map(this::fromDTO)
                       .collect(Collectors.toSet());
    }

}
