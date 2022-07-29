package com.example.bank.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    Admin,Client;

    @Override
    public String getAuthority() {
        return name();
    }
}
