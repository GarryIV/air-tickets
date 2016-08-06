package com.garryiv.air_tickets.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreApplication  {

    public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(CoreApplication.class)
				.properties("spring.config.name=core")
				.build()
				.run(args);

	}

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> servletContext.getSessionCookieConfig().setName("CORE_SESSION_ID");
    }
}
