package com.garryiv.air_tickets.api.flight;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface FlightService {

    @RequestMapping(method = RequestMethod.POST, value = "/api/flight")
    List<FlightInfo> findAll(@RequestBody FlightSearch search);

    @RequestMapping(method = RequestMethod.GET, value = "/api/flight/{flightId}")
    FlightInfo find(@PathVariable("flightId") Long flightId);
}
