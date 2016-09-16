package com.garryiv.air_tickets.core.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import com.garryiv.air_tickets.core.notification.email.EmailBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class CheckInNotificationJob {

    private final EmailBuilderFactory emailBuilderFactory;

    private final UserService userService;

    private final ReservationService reservationService;

    @Autowired
    public CheckInNotificationJob(
            EmailBuilderFactory emailBuilderFactory,
            UserService userService,
            ReservationService reservationService) {
        this.emailBuilderFactory = emailBuilderFactory;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    public void execute() {
        reservationService.findReservationsForCheckIn(new Date())
                .forEach(this::enqueueNotification);
    }

    private void enqueueNotification(ReservationInfo reservation) {
        UserInfo user = userService.find(reservation.getUserId());
        emailBuilderFactory.newEmail("check-in-notification")
                .withRecipient(user.getEmail())
                .withContext("reservation", reservation)
                .withContext("user", user)
                .enqueue();
    }

}
