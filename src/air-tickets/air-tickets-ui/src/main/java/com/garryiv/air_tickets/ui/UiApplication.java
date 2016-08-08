package com.garryiv.air_tickets.ui;

import com.garryiv.air_tickets.api.WebConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(jsr250Enabled = true, proxyTargetClass = true)
@Import({WebConfiguration.class})
public class UiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(UiApplication.class)
                .properties("spring.config.name=ui")
                .build()
                .run(args);
    }
}
