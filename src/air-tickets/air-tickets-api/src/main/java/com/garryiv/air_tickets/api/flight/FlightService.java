package com.garryiv.air_tickets.api.flight;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/api/flight")
public interface FlightService {

    @RequestMapping(method = RequestMethod.POST)
    List<FlightInfo> findAll(@RequestBody FlightSearch search);
}
