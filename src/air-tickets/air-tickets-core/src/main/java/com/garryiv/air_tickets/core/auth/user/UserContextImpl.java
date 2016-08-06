package com.garryiv.air_tickets.core.auth.user;

public class UserContextImpl implements UserContext {
    private final Long userId;
    private final String email;

    public UserContextImpl(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
