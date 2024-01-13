package com.devfadi.ecommercewebsite.config.filter;

import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;

@Component
@NonNullApi
public class JwtCheckFilter extends OncePerRequestFilter
{

    @Value("${AUTH0_DOMAIN}")
    private String auth0Domain;

    @Value("${AUTH0_AUDIENCE}")
    private String auth0Audience;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getBearerToken(request);
        if (StringUtils.hasText(token)) {
            try {
                DecodedJWT jwt = verifyToken(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        jwt.getSubject(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                throw new ServletException("Invalid token", e);
            }
        }

        filterChain.doFilter(request, response);
    }

    private DecodedJWT verifyToken(String token) throws Exception {
        JwkProvider jwkProvider = new JwkProviderBuilder(auth0Domain).build();
        DecodedJWT jwt = JWT.decode(token);
        RSAPublicKey publicKey = (RSAPublicKey) jwkProvider.get(jwt.getKeyId()).getPublicKey();
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        JWTVerifier verifier = JWT.require(algorithm)
                                  .withIssuer("https://" + auth0Domain + "/")
                                  .withAudience(auth0Audience)
                                  .build();
        return verifier.verify(token);
    }

    private String getBearerToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return authHeader;
    }
}

