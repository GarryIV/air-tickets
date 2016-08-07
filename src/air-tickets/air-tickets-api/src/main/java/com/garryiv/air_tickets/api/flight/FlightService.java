package com.garryiv.air_tickets.api.flight;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FlightService {

    @RequestMapping(method = RequestMethod.POST, value = "/api/flight")
    List<FlightInfo> findAll(@RequestBody FlightSearch search);

    @RequestMapping(method = RequestMethod.GET, value = "/api/flight/{flightId}")
    FlightInfo find(@RequestParam(name = "flightId") Long flightId);
}
