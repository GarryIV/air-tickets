package com.garryiv.air_tickets.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(jsr250Enabled = true, proxyTargetClass = true)
public class UiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(UiApplication.class)
                .properties("spring.config.name=ui")
                .build()
                .run(args);
    }
}
