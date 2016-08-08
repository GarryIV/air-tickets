package com.garryiv.air_tickets.api.flight;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface FlightService {

    /**
     * Search for scheduled flights
     * @param search search parameters
     * @return flights
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/flight")
    List<FlightInfo> findAll(@RequestBody FlightSearch search);

    /**
     * Find a flight by flight id or throw exception in case no flight with given id
     * @param flightId flight id
     * @return flight
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/flight/{flightId}")
    FlightInfo find(@PathVariable("flightId") Long flightId);

    /**
     * Cancel a flight
     * @param flightId flight id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/flight/{flightId}")
    void cancel(@PathVariable("flightId") Long flightId);
}
