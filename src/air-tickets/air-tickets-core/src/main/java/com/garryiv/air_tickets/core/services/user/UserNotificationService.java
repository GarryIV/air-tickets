package com.garryiv.air_tickets.core.services.user;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import com.garryiv.air_tickets.core.services.notification.Email;
import com.garryiv.air_tickets.core.services.notification.MessageBuilderFactory;
import com.garryiv.air_tickets.core.services.reservation.ReservationCancelledEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserNotificationService {

    private final MessageBuilderFactory messageBuilderFactory;
    private final UserService userService;
    private final NotificationQueue notificationQueue;

    @Autowired
    public UserNotificationService(MessageBuilderFactory messageBuilderFactory,
                                   UserService userService,
                                   NotificationQueue notificationQueue) {
        this.messageBuilderFactory = messageBuilderFactory;
        this.userService = userService;
        this.notificationQueue = notificationQueue;
    }

    @EventListener
    public void handleReservationCancelledEvent(ReservationCancelledEvent event) {
        ReservationInfo reservation = event.getReservation();
        UserInfo user = userService.find(reservation.getUserId());

        Email email = messageBuilderFactory.newEmail("reservation-cancelled")
                .withRecipient(user.getEmail())
                .withContext("reservation", reservation)
                .withContext("user", user)
                .build();

        notificationQueue.add(email);
    }
}
