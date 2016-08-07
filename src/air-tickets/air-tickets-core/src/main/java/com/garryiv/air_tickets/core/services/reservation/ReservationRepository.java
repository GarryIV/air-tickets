package com.garryiv.air_tickets.core.services.reservation;

import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Stream<Reservation> findByUserIdOrderByIdDesc(Long userId);
}
