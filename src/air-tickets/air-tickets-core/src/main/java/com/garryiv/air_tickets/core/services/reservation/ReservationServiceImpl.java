package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.flight.FlightService;
import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.reservation.ReservationRequest;
import com.garryiv.air_tickets.api.reservation.ReservationService;
import com.garryiv.air_tickets.api.reservation.ReservationStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final FlightService flightService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, FlightService flightService) {
        this.reservationRepository = reservationRepository;
        this.flightService = flightService;
    }

    @Override
    public ReservationInfo create(@RequestBody ReservationRequest request) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(request, reservation);
        reservation.setStatus(ReservationStatus.CREATED);
        reservation.setPrice(getPrice(request));

        reservation = reservationRepository.save(reservation);

        return toInfo(reservation);
    }

    @Override
    public List<ReservationInfo> findCurrentReservations(Long userId) {
        return reservationRepository.findByUserIdOrderByIdDesc(userId)
                .map(reservation -> toInfo(reservation))
                .collect(Collectors.toList());
    }

    private ReservationInfo toInfo(Reservation reservation) {
        ReservationInfo info = new ReservationInfo();
        BeanUtils.copyProperties(reservation, info);
        return info;
    }

    private BigDecimal getPrice(ReservationRequest request) {
        return flightService.find(request.getFlightId()).getPrice();
    }
}
