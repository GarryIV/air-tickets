package com.garryiv.air_tickets.core.services.user;

import com.garryiv.air_tickets.core.services.notification.Email;

public interface NotificationQueue {
    void add(Email email);
}
