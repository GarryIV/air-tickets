package com.garryiv.air_tickets.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableGlobalMethodSecurity(jsr250Enabled = true, proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories
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
