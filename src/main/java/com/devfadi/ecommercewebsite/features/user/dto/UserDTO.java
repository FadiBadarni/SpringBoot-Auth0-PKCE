package com.devfadi.ecommercewebsite.features.user.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("sub")
    private String auth0Id;

    private String email;

    @JsonProperty("name")
    private String fullName;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("email_verified")
    private Boolean emailVerified;

    private Set<String> roles;

}
