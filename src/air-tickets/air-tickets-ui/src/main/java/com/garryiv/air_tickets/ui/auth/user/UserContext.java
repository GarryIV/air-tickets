package com.garryiv.air_tickets.ui.auth.user;

import java.io.Serializable;

public interface UserContext extends Serializable {
    Long getUserId();

    String getEmail();
}
