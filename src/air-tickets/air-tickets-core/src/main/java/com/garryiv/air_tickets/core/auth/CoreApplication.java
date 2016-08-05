package com.garryiv.air_tickets.core.auth;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class CoreApplication  {


	@RequestMapping({ "/user", "/me" })
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
	}

    public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(CoreApplication.class)
				.properties("spring.config.name=core")
				.build()
				.run(args);

	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> servletContext.getSessionCookieConfig().setName("CORE_SESSION_ID");
    }
}
