package com.garryiv.air_tickets.core.services.flight;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.stream.Stream;

public interface FlightRepository extends PagingAndSortingRepository<Flight, Long>, QueryByExampleExecutor<Flight> {

    Stream<Flight> findAll(Specification<Flight> specification);
}
