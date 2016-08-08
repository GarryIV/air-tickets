package com.garryiv.air_tickets.ui.controllers;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.reservation.ReservationRequest;
import com.garryiv.air_tickets.api.reservation.ReservationService;
import com.garryiv.air_tickets.ui.auth.Roles;
import com.garryiv.air_tickets.ui.auth.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/my-reservation")
@RolesAllowed(Roles.PUBLIC)
public class MyReservationsController {

    private final UserContext userContext;

    private final ReservationService reservationService;

    @Autowired
    public MyReservationsController(
            ReservationService reservationService,
            UserContext userContext) {
        this.reservationService = reservationService;
        this.userContext = userContext;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ReservationInfo> currentReservations() {
        return reservationService.findUserReservations(userContext.getUserId());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/flight/{flightId}")
    public ReservationInfo create(@PathVariable("flightId") Long flightId) {
        ReservationRequest request = new ReservationRequest();
        request.setUserId(userContext.getUserId());
        request.setFlightId(flightId);
        return reservationService.create(request);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ReservationInfo get(@PathVariable("id") Long reservationId) {
        return reservationService.findUserReservation(userContext.getUserId(), reservationId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void cancel(@PathVariable("id") Long reservationId) {
        reservationService.cancelUserReservation(userContext.getUserId(), reservationId);
    }
}
