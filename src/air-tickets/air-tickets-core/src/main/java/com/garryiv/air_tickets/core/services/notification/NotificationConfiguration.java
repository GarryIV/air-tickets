package com.garryiv.air_tickets.core.services.notification;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MessageProperties.class)
public class NotificationConfiguration {
}
