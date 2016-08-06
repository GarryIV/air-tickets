package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.core.auth.Roles;
import com.garryiv.air_tickets.core.auth.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/my-reservation")
@RolesAllowed(Roles.PUBLIC)
public class MyReservationsController {

    private final UserContext userContext;

    private final ReservationService reservationService;

    @Autowired
    public MyReservationsController(ReservationService reservationService, UserContext userContext) {
        this.reservationService = reservationService;
        this.userContext = userContext;
    }

    @RequestMapping
    public List<Reservation> currentReservations() {
        return reservationService.findCurrentReservations(userContext.getUserId());
    }
}
