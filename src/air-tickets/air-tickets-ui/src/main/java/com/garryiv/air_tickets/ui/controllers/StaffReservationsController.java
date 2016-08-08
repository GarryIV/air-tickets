package com.garryiv.air_tickets.ui.controllers;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.reservation.ReservationService;
import com.garryiv.air_tickets.ui.auth.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RolesAllowed(Roles.STAFF)
public class StaffReservationsController {

    private final ReservationService reservationService;

    @Autowired
    public StaffReservationsController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flight/{flightId}")
    public List<ReservationInfo> findByFlight(@PathVariable("flightId") Long flightId) {
        return reservationService.findByFlight(flightId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void cancel(@PathVariable("id") Long reservationId) {
        reservationService.cancel(reservationId);
    }}
