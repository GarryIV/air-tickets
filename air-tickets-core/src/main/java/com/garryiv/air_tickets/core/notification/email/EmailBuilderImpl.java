package com.garryiv.air_tickets.core.notification.email;

import com.garryiv.air_tickets.core.notification.MessageProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailBuilderImpl implements EmailBuilder {
    private final EmailBuilderServiceImpl service;
    private final MessageProperties.EmailTemplate template;

    private String recipient;
    private Map<String, Object> contextMap = new HashMap<>();
    private String subject;

    public EmailBuilderImpl(EmailBuilderServiceImpl service, MessageProperties.EmailTemplate template) {
        this.service = service;
        this.template = template;
    }

    @Override
    public EmailBuilder withRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    @Override
    public EmailBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public EmailBuilderImpl withContext(String name, Object value) {
        contextMap.put(name, value);
        return this;
    }

    Email build() {
        byte[] content = service.buildContent(template.getLocation(), contextMap);
        List<EmailAttachment> attachmentInstances = new ArrayList<>();
        for (Map.Entry<String, String> entry : template.getAttachments().entrySet()) {
            String attachmentTemplate = entry.getValue();
            String attachmentName = entry.getKey();
            byte[] attachmentContent = service.buildAttachment(attachmentTemplate, contextMap);
            attachmentInstances.add(new EmailAttachment(attachmentName, attachmentContent));
        }
        return new Email(recipient, subject, content, attachmentInstances);
    }

    @Override
    public void enqueue() {
        service.enqueue(build());
    }
}
