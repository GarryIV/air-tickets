package com.garryiv.air_tickets.core.services.notification.queue;

import com.garryiv.air_tickets.core.services.notification.email.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements NotificationQueue {

    public static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Long add(Email email) {
        Notification notification = new Notification();
        notification.setStatus(NotificationStatus.PENDING);
        notification.setPayload(email);
        //notificationRepository.save(notification);
        logger.info("Email is enqueued: " + email);
        return notification.getId();
    }
}
