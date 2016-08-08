package com.garryiv.air_tickets.ui.auth.user;

import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.ui.auth.Roles;

import javax.annotation.security.RolesAllowed;

@RolesAllowed(Roles.PUBLIC)
public class UserContextImpl implements UserContext {
    private final UserInfo user;

    public UserContextImpl(UserInfo user) {
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
