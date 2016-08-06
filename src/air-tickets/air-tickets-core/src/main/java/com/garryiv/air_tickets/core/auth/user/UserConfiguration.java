package com.garryiv.air_tickets.core.auth.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class UserConfiguration {

    @Bean
    @SessionScope
    @Lazy
    public UserContext currentUser() {
        return new UserContextImpl(1L, "sdfsd");
    }
}
