package com.garryiv.air_tickets.core.auth.user;

import com.garryiv.air_tickets.core.services.user.User;

public class UserContextImpl implements UserContext {
    private final User user;

    public UserContextImpl(User user) {
        this.user = user;
    }

    @Override
    public Long getUserId() {
        return user.getId();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }
}
