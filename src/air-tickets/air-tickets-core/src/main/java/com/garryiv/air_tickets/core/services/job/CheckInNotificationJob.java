package com.garryiv.air_tickets.core.services.job;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import com.garryiv.air_tickets.core.services.notification.email.EmailBuilderFactory;
import com.garryiv.air_tickets.core.services.reservation.ReservationService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class CheckInNotificationJob implements Job {

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

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date from = context.getPreviousFireTime();
        reservationService.findReservationsForCheckIn(from)
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
