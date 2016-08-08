package com.garryiv.air_tickets.core.services.notification.queue;

import com.garryiv.air_tickets.core.services.notification.email.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class NotificationService implements NotificationQueue {

    private static final int MAX_FAILURES = 3;

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
        notification.setCreated(new Date());
        notification.setLastAttempt(new Date());
        notificationRepository.save(notification);
        logger.info("Email is enqueued: " + email);
        return notification.getId();
    }

    public List<Notification> findPendingNotifications() {
        return notificationRepository.findByStatusOrderByLastAttempt(NotificationStatus.PENDING);
    }

    public Notification success(Notification notification) {
        notification.setStatus(NotificationStatus.SENT);
        notification.setLastAttempt(new Date());
        return notificationRepository.save(notification);
    }

    public Notification failure(Notification notification) {
        notification.setFailures(notification.getFailures() + 1);
        if(notification.getFailures() >= MAX_FAILURES) {
            notification.setLastAttempt(new Date());
            notification.setStatus(NotificationStatus.ERROR);
        }
        return notificationRepository.save(notification);
    }
}
