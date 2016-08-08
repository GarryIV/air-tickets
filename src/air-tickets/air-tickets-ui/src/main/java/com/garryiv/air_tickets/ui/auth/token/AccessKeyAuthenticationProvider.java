package com.garryiv.air_tickets.ui.auth.token;

import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import com.garryiv.air_tickets.ui.auth.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public class AccessKeyAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;

    @Autowired
    public AccessKeyAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object principal = authentication.getPrincipal();
        String accessKey = (String) principal;
        UserInfo userInfo = userService.findByAccessKey(accessKey);
        if (userInfo == null) {
            throw new UnknownAccessKeyException(accessKey);
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(Roles.PUBLIC));
        return new UsernamePasswordAuthenticationToken(userInfo.getEmail(), "", authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AccessKeyToken.class.equals(authentication);
    }
}
