package com.garryiv.air_tickets.publ.google;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.security.oauth2.client.OAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public class GoogleOAuth2RestTemplateCustomizer implements UserInfoRestTemplateCustomizer {

    private OAuth2RequestAuthenticator authenticator;

    public GoogleOAuth2RestTemplateCustomizer(OAuth2RequestAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public void customize(OAuth2RestTemplate template) {
        authenticator = new GoogleOAuth2Authenticator();
        template.setAuthenticator(authenticator);
    }
}
