package com.garryiv.air_tickets.ui.auth.user;

import com.garryiv.air_tickets.ui.auth.Roles;

import javax.annotation.security.RolesAllowed;
import java.io.Serializable;

/**
 * Provides information about logged-in public user
 */
@RolesAllowed(Roles.PUBLIC)
public interface UserContext extends Serializable {
    Long getUserId();

    String getEmail();
}
