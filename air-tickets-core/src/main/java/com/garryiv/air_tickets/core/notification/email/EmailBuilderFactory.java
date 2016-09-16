package com.garryiv.air_tickets.core.notification.email;

public interface EmailBuilderFactory {
    EmailBuilder newEmail(String template);
}
