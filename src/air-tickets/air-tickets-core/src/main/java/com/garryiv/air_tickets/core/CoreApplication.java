package com.garryiv.air_tickets.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
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
}
