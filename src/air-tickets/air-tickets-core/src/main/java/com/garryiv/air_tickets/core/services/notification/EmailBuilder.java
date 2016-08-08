package com.garryiv.air_tickets.core.services.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailBuilder {
    private final MessageBuilderService service;
    private final MessageProperties.EmailTemplate template;

    private String recipient;
    private Map<String, Object> contextMap = new HashMap<>();

    public EmailBuilder(MessageBuilderService service, MessageProperties.EmailTemplate template) {
        this.service = service;
        this.template = template;
    }

    public EmailBuilder withRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public EmailBuilder withContext(String name, Object value) {
        contextMap.put(name, value);
        return this;
    }

    public Email build() {
        byte[] content = service.buildContent(template.getTemplate(), contextMap);
        List<EmailAttachment> attachmentInstances = new ArrayList<>();
        for (Map.Entry<String, String> entry : template.getAttachments().entrySet()) {
            String attachmentTemplate = entry.getKey();
            String attachmentName = entry.getValue();
            byte[] attachmentContent = service.buildAttachment(attachmentTemplate, contextMap);
            attachmentInstances.add(new EmailAttachment(attachmentName, attachmentContent));
        }
        return new Email(recipient, content, attachmentInstances);
    }
}
