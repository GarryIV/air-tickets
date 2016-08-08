package com.garryiv.air_tickets.ui.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetailsSource;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableOAuth2Client
@EnableAuthorizationServer
@Order(6)
public class UiSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/login/staff")
                    .failureHandler(new SendErrorFailureHandler(HttpServletResponse.SC_FORBIDDEN))
                    .successHandler(new SimpleUrlAuthenticationSuccessHandler("/user"))
                .and()
                    .antMatcher("/**").authorizeRequests()
                    .antMatchers("/", "/login**", "/flight", "/api/**", "/webjars/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
                .and()
                    .logout().logoutSuccessUrl("/").permitAll()
                .and()
                    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                    .addFilterAfter(ssoFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    @ConfigurationProperties
    ProvidersProperties providers() {
        return new ProvidersProperties();
    }

    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = providers().getOauth2Providers().values().stream()
                .map(this::ssoFilter)
                .collect(Collectors.toList());
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(ProviderProperties client) {

        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                client.getResource().getUserInfoUri(),
                client.getClient().getClientId()
        );
        tokenServices.setPrincipalExtractor(new SpELPrincipalExtractor(client));
        tokenServices.setAuthoritiesExtractor(map ->
                Collections.singletonList(new SimpleGrantedAuthority(Roles.PUBLIC)));
        tokenServices.setRestTemplate(oAuth2RestTemplate);

        OAuth2ClientAuthenticationProcessingFilter filter
                = new OAuth2ClientAuthenticationProcessingFilter(client.getPath());
        filter.setRestTemplate(oAuth2RestTemplate);
        filter.setTokenServices(tokenServices);
        filter.setAuthenticationDetailsSource(new OAuth2AuthenticationDetailsSource());
        return filter;
    }

    public static class ProvidersProperties {
        private Map<String, ProviderProperties> oauth2Providers = new HashMap<>();

        public Map<String, ProviderProperties> getOauth2Providers() {
            return oauth2Providers;
        }
    }

    public static class ProviderProperties {
        private OAuth2ProtectedResourceDetails client = new AuthorizationCodeResourceDetails();
        private ResourceServerProperties resource = new ResourceServerProperties();
        private String path;
        private String emailField = "email";

        public OAuth2ProtectedResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getEmailField() {
            return emailField;
        }

        public void setEmailField(String emailField) {
            this.emailField = emailField;
        }
    }

    private static class SpELPrincipalExtractor implements PrincipalExtractor {

        private final Expression expression;

        public SpELPrincipalExtractor(ProviderProperties client) {
            expression = new SpelExpressionParser().parseExpression("#" + client.getEmailField());
        }

        @Override
        public Object extractPrincipal(Map<String, Object> map) {
            StandardEvaluationContext context = new StandardEvaluationContext();
            context.setVariables(map);
            return expression.getValue(context);
        }
    }

    private static class SendErrorFailureHandler implements AuthenticationFailureHandler {

        private int errorCode;

        public SendErrorFailureHandler(int errorCode) {
            this.errorCode = errorCode;
        }

        @Override
        public void onAuthenticationFailure(
                HttpServletRequest request,
                HttpServletResponse response,
                AuthenticationException exception) throws IOException, ServletException {
            response.sendError(errorCode, exception.getMessage());
        }
    }
}
