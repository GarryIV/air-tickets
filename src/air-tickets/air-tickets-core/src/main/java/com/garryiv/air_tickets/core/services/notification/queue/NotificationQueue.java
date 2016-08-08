package com.garryiv.air_tickets.core.services.notification.queue;

import com.garryiv.air_tickets.core.services.notification.email.Email;

public interface NotificationQueue {
    Long add(Email email);
}
