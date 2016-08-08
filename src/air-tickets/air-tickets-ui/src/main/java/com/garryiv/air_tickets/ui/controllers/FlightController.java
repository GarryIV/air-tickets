package com.garryiv.air_tickets.ui.controllers;

import com.garryiv.air_tickets.api.flight.FlightInfo;
import com.garryiv.air_tickets.api.flight.FlightSearch;
import com.garryiv.air_tickets.api.flight.FlightService;
import com.garryiv.air_tickets.ui.auth.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public List<FlightInfo> findAll(@RequestBody FlightSearch search) {
        return flightService.searchScheduled(search);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{flightId}")
    public FlightInfo find(@PathVariable("flightId") Long flightId) {
        return flightService.find(flightId);
    }

    @RolesAllowed(Roles.STAFF)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{flightId}")
    public void cancel(@PathVariable("flightId") Long flightId) {
        flightService.cancel(flightId);
    }
}
