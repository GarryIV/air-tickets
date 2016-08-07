package com.garryiv.air_tickets.api.reservation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ReservationService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/reservation/user/{userId}")
    List<ReservationInfo> findCurrentReservations(@PathVariable("userId") Long userId);
}
