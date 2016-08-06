package com.garryiv.air_tickets.core.auth.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class AuthenticationContextConfiguration {

    @Bean
    @SessionScope
    @Lazy
    public UserContext currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new UserContextImpl(1L, authentication.getName());
    }
}
