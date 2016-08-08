package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.reservation.ReservationStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.stream.Stream;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Stream<Reservation> findByUserIdOrderByIdDesc(Long userId);

    Reservation findByIdAndUserId(Long id, Long userId);

    Stream<ReservationInfo> findByDepartureBetweenAndStatus(Date adjustedFrom, Date to, ReservationStatus paid);
}
