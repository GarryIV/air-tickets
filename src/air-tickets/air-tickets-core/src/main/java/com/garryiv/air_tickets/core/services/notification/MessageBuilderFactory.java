package com.garryiv.air_tickets.core.services.notification;

public interface MessageBuilderFactory {
    EmailBuilder newEmail(String template);
}
