package com.garryiv.air_tickets.core.notification.queue;

import com.garryiv.air_tickets.core.notification.email.Email;

public interface NotificationQueue {
    Long add(Email email);
}
