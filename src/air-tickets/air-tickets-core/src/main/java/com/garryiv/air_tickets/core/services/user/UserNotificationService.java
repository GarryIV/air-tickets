package com.garryiv.air_tickets.core.services.user;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import com.garryiv.air_tickets.core.services.notification.email.EmailBuilderFactory;
import com.garryiv.air_tickets.core.services.reservation.ReservationCancelledEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserNotificationService {

    private final EmailBuilderFactory emailBuilderFactory;
    private final UserService userService;

    @Autowired
    public UserNotificationService(EmailBuilderFactory emailBuilderFactory, UserService userService) {
        this.emailBuilderFactory = emailBuilderFactory;
        this.userService = userService;
    }

    @EventListener
    public void handleReservationCancelledEvent(ReservationCancelledEvent event) {
        ReservationInfo reservation = event.getReservation();
        UserInfo user = userService.find(reservation.getUserId());

        emailBuilderFactory.newEmail("reservation-cancelled")
                .withRecipient(user.getEmail())
                .withContext("reservation", reservation)
                .withContext("user", user)
                .enqueue();
    }
}
