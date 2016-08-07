package com.garryiv.air_tickets.core.services.notification;

import com.garryiv.air_tickets.core.services.reservation.ReservationCancelledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserNotificationService {

    @EventListener
    public void handleReservationCancelledEvent(ReservationCancelledEvent event) {
        //TODO: Generate email
    }
}
