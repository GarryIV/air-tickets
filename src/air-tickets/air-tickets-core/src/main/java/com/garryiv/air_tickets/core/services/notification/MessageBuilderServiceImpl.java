package com.garryiv.air_tickets.core.services.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageBuilderServiceImpl implements MessageBuilderService {

    private MessageBuilderService self;

    private MessageProperties messageProperties;

    @Autowired
    public void init(MessageBuilderService self, MessageProperties messageProperties) {
        this.self = self;
        this.messageProperties = messageProperties;
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

        return new EmailBuilder(self, emailTemplate);
    }

    @Override
    public byte[] buildContent(String template, Map<String, Object> contextMap) {
        return new byte[0];
    }

    @Override
    public byte[] buildAttachment(String template, Map<String, Object> contextMap) {
        return new byte[0];
    }
}
