package com.devfadi.ecommercewebsite.features.user.entity;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum Role
{

    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public SimpleGrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority(roleName);
    }
}
