package com.devfadi.ecommercewebsite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig
{

    private static final List<String> DEFAULT_ALLOWED_METHODS = List.of("GET", "POST", "PUT", "DELETE", "OPTIONS",
                                                                        "PATCH");
    private static final List<String> DEFAULT_ALLOWED_HEADERS = List.of("*");
    @Value("${app.cors.allowed_origins}")
    private String allowedOrigins;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration defaultConfiguration = createDefaultCorsConfiguration();
        source.registerCorsConfiguration("/**", defaultConfiguration);

        return source;
    }

    private CorsConfiguration createDefaultCorsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
        configuration.setAllowedMethods(DEFAULT_ALLOWED_METHODS);
        configuration.setAllowedHeaders(DEFAULT_ALLOWED_HEADERS);
        configuration.setAllowCredentials(true);
        return configuration;
    }

}