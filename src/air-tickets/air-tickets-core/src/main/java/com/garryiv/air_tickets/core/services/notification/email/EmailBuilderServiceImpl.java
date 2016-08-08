package com.garryiv.air_tickets.core.services.notification.email;

import com.garryiv.air_tickets.core.services.notification.MessageProperties;
import com.garryiv.air_tickets.core.services.notification.queue.NotificationQueue;
import com.garryiv.air_tickets.core.services.notification.template.TemplateInterpolator;

import java.util.Map;


public class EmailBuilderServiceImpl implements EmailBuilderService {

    private final MessageProperties messageProperties;
    private final NotificationQueue notificationQueue;
    private final TemplateInterpolator contentInterpolator;
    private final TemplateInterpolator attachmentInterpolator;

    public EmailBuilderServiceImpl(
            MessageProperties messageProperties,
            NotificationQueue notificationQueue,
            TemplateInterpolator contentInterpolator,
            TemplateInterpolator attachmentInterpolator) {
        this.messageProperties = messageProperties;
        this.notificationQueue = notificationQueue;
        this.contentInterpolator = contentInterpolator;
        this.attachmentInterpolator = attachmentInterpolator;
    }

    @Override
    public EmailBuilder newEmail(String template) {
        if(template == null) {
            throw new IllegalArgumentException("template is required");
        }

        MessageProperties.EmailTemplate emailTemplate = messageProperties.getTemplates().get(template);
        if (emailTemplate == null) {
            throw new IllegalArgumentException("Template is not found: "  + template);
        }

        return new EmailBuilderImpl(this, emailTemplate);
    }

    byte[] buildContent(String template, Map<String, Object> contextMap) {
        return contentInterpolator.interpolate(template, contextMap);
    }

    byte[] buildAttachment(String template, Map<String, Object> contextMap) {
        return attachmentInterpolator.interpolate(template, contextMap);
    }

    void enqueue(Email email) {
        notificationQueue.add(email);
    }
}
