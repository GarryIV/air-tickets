package com.garryiv.air_tickets.core.services;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.*;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE, properties = "spring.config.name=core")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CoreSpringBootTest {
}
