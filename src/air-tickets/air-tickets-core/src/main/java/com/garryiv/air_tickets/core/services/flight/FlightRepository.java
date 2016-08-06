package com.garryiv.air_tickets.core.services.flight;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FlightRepository extends PagingAndSortingRepository<Flight, Long> {
}
