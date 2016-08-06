package com.garryiv.air_tickets.core.reservation;

import com.garryiv.air_tickets.core.auth.Roles;
import com.garryiv.air_tickets.core.auth.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/my-reservation")
@RolesAllowed(Roles.PUBLIC)
public class MyReservationsController {
    private final UserContext userContext;

    @Autowired
    public MyReservationsController(UserContext userContext) {
        this.userContext = userContext;
    }

    @RequestMapping
    public List<Reservation> getAllReservation() {
        Long userId = userContext.getUserId();
        return new ArrayList<>();
    }
}
