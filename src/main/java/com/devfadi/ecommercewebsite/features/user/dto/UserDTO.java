package com.devfadi.ecommercewebsite.features.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO
{
    private String auth0Id;

    private String email;

    private String fullName;

    private String profilePicture;

    private Boolean emailVerified;

    private Set<String> roles;

}
