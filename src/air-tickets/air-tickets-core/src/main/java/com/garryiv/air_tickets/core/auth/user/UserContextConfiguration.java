package com.garryiv.air_tickets.core.auth.user;

import com.garryiv.air_tickets.core.services.user.User;
import com.garryiv.air_tickets.core.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Exposes current user information
 */
@Configuration
public class UserContextConfiguration {

    private final UserService userService;

    @Autowired
    public UserContextConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Bean
    @SessionScope
    @Lazy
    public UserContext currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findOrCreate(authentication.getName());
        return new UserContextImpl(user);
    }
}
