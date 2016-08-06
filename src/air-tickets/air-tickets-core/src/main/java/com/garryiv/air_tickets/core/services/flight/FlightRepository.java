package com.garryiv.air_tickets.core.services.flight;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface FlightRepository extends PagingAndSortingRepository<Flight, Long>, QueryByExampleExecutor<Flight> {

    Page<Flight> findAll(Specification<Flight> specification, Pageable pageable);
}
