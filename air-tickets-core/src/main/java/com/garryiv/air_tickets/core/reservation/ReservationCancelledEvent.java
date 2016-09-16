package com.garryiv.air_tickets.core.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import org.springframework.context.ApplicationEvent;

public class ReservationCancelledEvent extends ApplicationEvent {

    private final ReservationInfo reservation;

    public ReservationCancelledEvent(ReservationInfo reservation) {
        super(reservation);
        this.reservation = reservation;
    }

    public ReservationInfo getReservation() {
        return reservation;
    }
}
