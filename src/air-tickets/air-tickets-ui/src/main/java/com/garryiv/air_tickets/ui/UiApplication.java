package com.garryiv.air_tickets.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(UiApplication.class)
                .properties("spring.config.name=ui")
                .build()
                .run(args);
    }
}
