package com.garryiv.air_tickets.core.services.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "email")
public class MessageProperties {
    public Map<String, EmailTemplate> templates = new HashMap<>();

    public Map<String, EmailTemplate> getTemplates() {
        return templates;
    }

    public static class EmailTemplate {
        private String template;
        private Map<String, String> attachments = new HashMap<>();

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public Map<String, String> getAttachments() {
            return attachments;
        }

        public void setAttachments(Map<String, String> attachments) {
            this.attachments = attachments;
        }
    }
}
