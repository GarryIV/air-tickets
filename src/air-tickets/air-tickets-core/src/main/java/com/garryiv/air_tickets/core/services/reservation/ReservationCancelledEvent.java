package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import org.springframework.context.ApplicationEvent;

public class ReservationCancelledEvent extends ApplicationEvent {
    public ReservationCancelledEvent(ReservationInfo reservationInfo) {
        super(reservationInfo);
    }
}
