package com.garryiv.air_tickets.core.services.notification.queue;

import com.garryiv.air_tickets.core.services.notification.email.Email;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements NotificationQueue {

    @Override
    public Long add(Email email) {
        Notification notification = new Notification();
        notification.setStatus(NotificationStatus.PENDING);
        return null;
    }
}
