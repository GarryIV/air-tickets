package com.garryiv.air_tickets.core.services.notification.queue;

import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
