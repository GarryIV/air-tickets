package com.garryiv.air_tickets.ui.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AccessKeyToken extends AbstractAuthenticationToken {
    private String token;

    public AccessKeyToken(String token) {
        super(null);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
