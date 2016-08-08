package com.garryiv.air_tickets.core.services.notification;

import com.garryiv.air_tickets.core.services.notification.email.EmailBuilderFactory;
import com.garryiv.air_tickets.core.services.notification.email.EmailBuilderServiceImpl;
import com.garryiv.air_tickets.core.services.notification.queue.NotificationQueue;
import com.garryiv.air_tickets.core.services.notification.template.HtmlTemplateInterpolator;
import com.garryiv.air_tickets.core.services.notification.template.PdfTemplateInterpolator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MessageProperties.class)
public class NotificationConfiguration {

    @Bean
    public HtmlTemplateInterpolator htmlTemplateInterpolator() {
        return new HtmlTemplateInterpolator();
    }

    @Bean
    public PdfTemplateInterpolator pdfTemplateInterpolator() {
        return new PdfTemplateInterpolator();
    }

    @Bean
    public EmailBuilderFactory emailBuilderFactory(MessageProperties properties, NotificationQueue notificationQueue) {
        return new EmailBuilderServiceImpl(
                properties,
                notificationQueue,
                htmlTemplateInterpolator(),
                pdfTemplateInterpolator()
        );
    }
}
