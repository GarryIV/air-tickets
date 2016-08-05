package com.garryiv.air_tickets.publ.google;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("google")
public class GoogleOAuth2Configuration {

    @Bean
    public GoogleOAuth2Authenticator googleOAuth2Authenticator() {
        return new GoogleOAuth2Authenticator();
    }

    @Bean
    public GoogleOAuth2RestTemplateCustomizer customOAuth2RestTemplate() {
        return new GoogleOAuth2RestTemplateCustomizer(googleOAuth2Authenticator());
    }

}
