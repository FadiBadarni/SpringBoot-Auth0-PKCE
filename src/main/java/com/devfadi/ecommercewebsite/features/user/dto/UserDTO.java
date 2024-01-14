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
public class UserDTO {

    private Long id;

    private String auth0Id;

    private String email;

    private String firstName;

    private String lastName;

    private String profilePicture;

    private String locale;

    private Boolean emailVerified;

    private Set<String> roles;

}