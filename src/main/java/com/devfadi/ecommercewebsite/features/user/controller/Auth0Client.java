package com.devfadi.ecommercewebsite.features.user.controller;

import com.devfadi.ecommercewebsite.exception.UserInfoException;
import com.devfadi.ecommercewebsite.features.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Auth0Client
{

    private final RestTemplate restTemplate;
    private final String userInfoEndpoint;

    public Auth0Client(RestTemplateBuilder restTemplateBuilder,
                       @Value("${app.auth0.user_info_url}") String userInfoEndpoint) {
        this.restTemplate = restTemplateBuilder.build();
        this.userInfoEndpoint = userInfoEndpoint;
    }

    public UserDTO getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(userInfoEndpoint, HttpMethod.GET,
                                                                                 entity,
                                                                                 new ParameterizedTypeReference<>()
                                                                                 {
                                                                                 });

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return mapToUserDTO(response.getBody());
            } else {
                throw new UserInfoException("No user information found", null);
            }
        } catch (RestClientException e) {
            throw new UserInfoException("Error retrieving user info from Auth0: " + e.getMessage(), e);
        }
    }

    private UserDTO mapToUserDTO(Map<String, Object> userInfo) {
        UserDTO userDTO = new UserDTO();
        userDTO.setAuth0Id((String) userInfo.get("sub"));
        userDTO.setEmail((String) userInfo.get("email"));
        userDTO.setFullName((String) userInfo.get("name"));
        userDTO.setProfilePicture((String) userInfo.get("picture"));
        userDTO.setEmailVerified((Boolean) userInfo.get("email_verified"));
        return userDTO;
    }
}
