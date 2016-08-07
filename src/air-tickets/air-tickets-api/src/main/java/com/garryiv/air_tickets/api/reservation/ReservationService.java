package com.garryiv.air_tickets.api.reservation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    ReservationInfo create(@RequestBody ReservationRequest reservationRequest);

    /**
     * Find all user reservations
     * @param userId owner's user ir
     * @return users reservation in order they created, descending
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/reservation/user/{userId}")
    List<ReservationInfo> findUserReservations(@PathVariable("userId") Long userId);

    /**
     * Find user's reservation or throw exception if reservationId not found or it belongs to different userId
     * @param userId user id
     * @param reservationId reservation id
     * @return reservation
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/reservation/user/{userId}/{reservationId}")
    ReservationInfo findUserReservation(@PathVariable("userId") Long userId,
                                        @PathVariable("reservationId")  Long reservationId);

    /**
     * Cancel user's reservation or throw exception if reservationId not found, belongs to different userId
     * or it's in inappropriate state
     * @param userId user id
     * @param reservationId reservation id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/reservation/user/{userId}/{reservationId}")
    void cancelUserReservation(@PathVariable("userId") Long userId,
                               @PathVariable("reservationId")  Long reservationId);
}
