package com.garryiv.air_tickets.core.notification.queue;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    List<Notification> findByStatusOrderByLastAttempt(NotificationStatus status);
}
