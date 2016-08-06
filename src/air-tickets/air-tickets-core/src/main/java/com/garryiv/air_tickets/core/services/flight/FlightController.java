package com.garryiv.air_tickets.core.services.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ReadingConverter
@RequestMapping("/api/flight")
public class FlightController {

    static final Sort DEPARTURE_ASC = new Sort(new Sort.Order(Sort.Direction.ASC, "departure"));

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping
    public Page<Flight> findAll(@RequestBody Flight example) {
        return flightService.findAll(example, new PageRequest(0, 100, DEPARTURE_ASC));
    }
}
