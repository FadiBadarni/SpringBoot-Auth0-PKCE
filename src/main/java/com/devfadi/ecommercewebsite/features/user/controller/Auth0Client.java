package com.devfadi.ecommercewebsite.features.user.controller;

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
public class Auth0Client {

    private final RestTemplate restTemplate;
    private final String userInfoEndpoint;

    public Auth0Client(RestTemplateBuilder restTemplateBuilder,
                       @Value("${app.auth0.user_info_url}") String userInfoEndpoint) {
        this.restTemplate = restTemplateBuilder.build();
        this.userInfoEndpoint = userInfoEndpoint;
    }

    public UserDTO getUserInfo(String accessToken) throws RestClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<UserDTO> response = restTemplate.exchange(
                userInfoEndpoint, HttpMethod.GET, entity, UserDTO.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RestClientException("Failed to retrieve user info from Auth0");
        }
    }
}