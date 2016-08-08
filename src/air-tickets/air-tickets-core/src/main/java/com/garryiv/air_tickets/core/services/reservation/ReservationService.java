package com.garryiv.air_tickets.core.services.reservation;


import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.core.services.flight.FlightCancelledEvent;
import org.springframework.context.event.EventListener;

import java.util.Date;
import java.util.List;

public interface ReservationService extends com.garryiv.air_tickets.api.reservation.ReservationService {

    /**
     * Find reservations ready to check-in
     * @param from departure starting from
     * @return list of matching reservations
     */
    List<ReservationInfo> findReservationsForCheckIn(Date from);

    /**
     * Handles FlightCancelledEvent by cancelling all pending flight reservations
     * @param event source event
     */
    @EventListener
    void handleFlightCancelledEvent(FlightCancelledEvent event);
}
