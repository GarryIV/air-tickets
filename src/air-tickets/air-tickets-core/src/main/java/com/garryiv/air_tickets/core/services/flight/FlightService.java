package com.garryiv.air_tickets.core.services.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Filter by origin, destination and departure day
     * @param example example flight
     * @param pageable pagination and sort order
     * @return page
     */
    public Page<Flight> findAll(Flight example, Pageable pageable) {
        return flightRepository.findAll(FlightSpecs.from(example), pageable);
    }
}
