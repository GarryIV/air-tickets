package com.garryiv.air_tickets.api.reservation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Manages reservations.
 */
public interface ReservationService {

    /**
     * Create a new reservation upon user request
     * @param reservationRequest reservation request
     * @return new reservation
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/api/reservation")
    ReservationInfo create(ReservationRequest reservationRequest);

    /**
     * Find all user reservations
     * @param userId owner's user ir
     * @return users reservation in order they created, descending
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/reservation/user/{userId}")
    List<ReservationInfo> findCurrentReservations(@PathVariable("userId") Long userId);
}
