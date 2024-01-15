package com.devfadi.ecommercewebsite.mapper;

import com.devfadi.ecommercewebsite.features.user.dto.UserDTO;
import com.devfadi.ecommercewebsite.features.user.entity.Role;
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

        return UserDTO.builder()
                      .auth0Id(user.getAuth0Id())
                      .email(user.getEmail())
                      .fullName(user.getFullName())
                      .profilePicture(user.getProfilePicture())
                      .emailVerified(user.getEmailVerified())
                      .role(user.getRole() != null ?
                            user.getRole() :
                            Role.USER)
                      .addresses(user.getAddresses())
                      .paymentPreference(user.getPaymentPreference())
                      .preferences(user.getPreferences())
                      .contactPreference(user.getContactPreference())
                      .accountStatus(user.getAccountStatus())
                      .language(user.getLanguage())
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
                   .role(userDTO.getRole() != null ?
                         userDTO.getRole() :
                         Role.USER)
                   .addresses(userDTO.getAddresses())
                   .paymentPreference(userDTO.getPaymentPreference())
                   .preferences(userDTO.getPreferences())
                   .contactPreference(userDTO.getContactPreference())
                   .accountStatus(userDTO.getAccountStatus())
                   .language(userDTO.getLanguage())
                   .build();
    }

}
