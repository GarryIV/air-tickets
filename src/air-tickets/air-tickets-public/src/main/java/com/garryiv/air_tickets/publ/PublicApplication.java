package com.garryiv.air_tickets.publ;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PublicApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(PublicApplication.class)
                .properties("spring.config.name=public")
                .build(args)
                .run();
    }
}
