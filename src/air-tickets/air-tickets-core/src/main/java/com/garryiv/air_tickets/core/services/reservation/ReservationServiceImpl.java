package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.flight.FlightInfo;
import com.garryiv.air_tickets.api.flight.FlightService;
import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.reservation.ReservationRequest;
import com.garryiv.air_tickets.api.reservation.ReservationStatus;
import com.garryiv.air_tickets.core.services.flight.FlightCancelledEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {

    static final int LATEST_CHECK_IN = 4;
    static final int EARLIEST_CHECK_IN = 48;

    private final ApplicationEventPublisher eventPublisher;

    private final ReservationRepository reservationRepository;

    private final FlightService flightService;

    @Autowired
    public ReservationServiceImpl(ApplicationEventPublisher eventPublisher,
                                  ReservationRepository reservationRepository,
                                  FlightService flightService) {
        this.eventPublisher = eventPublisher;
        this.reservationRepository = reservationRepository;
        this.flightService = flightService;
    }

    @Override
    public ReservationInfo create(@RequestBody ReservationRequest request) {
        FlightInfo flightInfo = flightService.find(request.getFlightId());

        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(request, reservation);

        reservation.setStatus(ReservationStatus.CREATED);

        // copy info from the flight
        reservation.setFlightNumber(flightInfo.getFlightNumber());
        reservation.setPrice(flightInfo.getPrice());
        reservation.setOrigin(flightInfo.getOrigin());
        reservation.setDestination(flightInfo.getDestination());
        reservation.setDeparture(flightInfo.getDeparture());
        reservation.setArrival(flightInfo.getArrival());

        reservation = reservationRepository.save(reservation);

        return toInfo(reservation);
    }

    @Override
    public List<ReservationInfo> findUserReservations(@PathVariable Long userId) {
        Assert.notNull(userId, "userId");

        return reservationRepository.findByUserIdOrderByIdDesc(userId)
                .map(reservation -> toInfo(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationInfo findUserReservation(@PathVariable Long userId, @PathVariable Long reservationId) {
        Assert.notNull(userId, "userId");
        Assert.notNull(reservationId, "reservationId");
        return toInfo(reservationRepository.findByIdAndUserId(reservationId, userId));
    }

    @Override
    public void cancelUserReservation(@PathVariable Long userId, @PathVariable Long reservationId) {
        Reservation reservation = reservationRepository.findByIdAndUserId(reservationId, userId);
        doCancel(reservation);
    }

    @Override
    @Transactional
    public List<ReservationInfo> findReservationsForCheckIn(Date from) {
        Date latestCheckIn = DateUtils.addHours(new Date(), LATEST_CHECK_IN);
        Date adjustedFrom = ObjectUtils.max(from, latestCheckIn);
        Date to = DateUtils.addHours(new Date(), EARLIEST_CHECK_IN);
        return reservationRepository.findByDepartureBetweenAndStatus(adjustedFrom, to, ReservationStatus.PAID)
                .map(this::toInfo)
                .collect(Collectors.toList());
    }

    @Override
    @EventListener
    public void handleFlightCancelledEvent(FlightCancelledEvent event) {
        FlightInfo flight = event.getFlight();
        reservationRepository.findByFlightIdAndStatusNot(flight.getId(), ReservationStatus.CANCELLED)
                .forEach(this::doCancel);
    }

    private void doCancel(Reservation reservation) {
        if(reservation.getStatus() == ReservationStatus.CANCELLED) {
            // already cancelled
            return;
        }
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
        eventPublisher.publishEvent(new ReservationCancelledEvent(toInfo(reservation)));
    }

    private ReservationInfo toInfo(Reservation reservation) {
        Assert.notNull(reservation, "reservation");

        ReservationInfo info = new ReservationInfo();
        BeanUtils.copyProperties(reservation, info);
        return info;
    }

}
