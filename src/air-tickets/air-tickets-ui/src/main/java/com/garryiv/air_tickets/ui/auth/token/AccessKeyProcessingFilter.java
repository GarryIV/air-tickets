package com.garryiv.air_tickets.ui.auth.token;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Searches in LOGIN_TOKEN request parameter
 */
public class AccessKeyProcessingFilter extends OncePerRequestFilter {

    private static final String ACCESS_TOKEN = "access-key"
            ;
    private final AuthenticationManager authenticationManager;

    public AccessKeyProcessingFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getParameter(ACCESS_TOKEN);
        if(StringUtils.isNotEmpty(token)) {
            Authentication currentUser = SecurityContextHolder.getContext()
                    .getAuthentication();

            if (currentUser == null) {
                Authentication authentication = authenticationManager.authenticate(new AccessKeyToken(token));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // strip query string
            response.sendRedirect(request.getRequestURL().toString());
            return;
        }
        filterChain.doFilter(request, response);
    }

}
