package com.garryiv.air_tickets.core.services.reservation;


import com.garryiv.air_tickets.api.reservation.ReservationInfo;

import java.util.Date;
import java.util.stream.Stream;

public interface ReservationService extends com.garryiv.air_tickets.api.reservation.ReservationService {

    /**
     * Find reservations ready to check-in
     * @param from flight departure from
     * @param to flight departure to
     * @return list of matching reservations
     */
    Stream<ReservationInfo> findReservationsForCheckIn(Date from, Date to);
}
