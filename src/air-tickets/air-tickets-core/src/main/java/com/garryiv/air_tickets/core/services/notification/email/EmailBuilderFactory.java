package com.garryiv.air_tickets.core.services.notification.email;

public interface EmailBuilderFactory {
    EmailBuilder newEmail(String template);
}
