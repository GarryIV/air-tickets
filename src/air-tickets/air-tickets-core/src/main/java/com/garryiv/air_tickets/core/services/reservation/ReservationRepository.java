package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.stream.Stream;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Stream<Reservation> findByUserIdOrderByIdDesc(Long userId);

    Reservation findByIdAndUserId(Long id, Long userId);

    Stream<Reservation> findByDepartureBetweenAndStatus(Date adjustedFrom, Date to, ReservationStatus paid);

    Stream<Reservation> findByFlightIdAndStatusNot(Long flightId, ReservationStatus status);
}
