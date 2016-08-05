package com.garryiv.air_tickets.publ;

import com.garryiv.air_tickets.publ.auth.token.TokenPreAuthenticatedProcessingFilter;
import com.garryiv.air_tickets.publ.auth.token.TokenPreAuthenticationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableOAuth2Sso
public class PublicSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public TokenPreAuthenticationUserDetailsService tokenPreAuthenticationUserDetailsService() {
        return new TokenPreAuthenticationUserDetailsService();
    }

    @Bean
    public AuthenticationProvider tokenPreAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(tokenPreAuthenticationUserDetailsService());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(tokenPreAuthenticationUserDetailsService());
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .logout().logoutSuccessUrl("/").permitAll()
                .and()
                    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        TokenPreAuthenticatedProcessingFilter filter = new TokenPreAuthenticatedProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setApplicationEventPublisher(http.getSharedObject(ApplicationContext.class));
        http.addFilter(filter);
    }
}
