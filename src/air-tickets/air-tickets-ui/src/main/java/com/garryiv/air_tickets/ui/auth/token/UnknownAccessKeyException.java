package com.garryiv.air_tickets.ui.auth.token;

import org.springframework.security.core.AuthenticationException;

public class UnknownAccessKeyException extends AuthenticationException {
    public UnknownAccessKeyException(String msg) {
        super(msg);
    }
}
