package com.garryiv.air_tickets.core.services.notification;

import com.garryiv.air_tickets.core.services.notification.email.EmailBuilderFactory;
import com.garryiv.air_tickets.core.services.notification.email.EmailBuilderServiceImpl;
import com.garryiv.air_tickets.core.services.notification.queue.NotificationQueue;
import com.garryiv.air_tickets.core.services.notification.template.HtmlTemplateInterpolator;
import com.garryiv.air_tickets.core.services.notification.template.PdfTemplateInterpolator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
@EnableConfigurationProperties(MessageProperties.class)
public class NotificationConfiguration {

    private final ResourceLoader resourceLoader;

    @Autowired
    public NotificationConfiguration(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public HtmlTemplateInterpolator htmlTemplateInterpolator() {
        return new HtmlTemplateInterpolator();
    }

    @Bean
    public PdfTemplateInterpolator pdfTemplateInterpolator() {
        return new PdfTemplateInterpolator(resourceLoader);
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
