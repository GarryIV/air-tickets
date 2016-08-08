package com.garryiv.air_tickets.core.services.notification.email;

public interface EmailBuilder {
    EmailBuilder withRecipient(String recipient);
    EmailBuilder withSubject(String subject);
    EmailBuilder withContext(String name, Object value);

    void enqueue();
}
