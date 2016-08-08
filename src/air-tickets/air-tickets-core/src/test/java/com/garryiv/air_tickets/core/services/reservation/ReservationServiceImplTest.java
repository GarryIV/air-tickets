package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.reservation.ReservationRequest;
import com.garryiv.air_tickets.api.reservation.ReservationStatus;
import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import com.garryiv.air_tickets.core.services.CoreServiceTest;
import com.garryiv.air_tickets.core.services.flight.Flight;
import com.garryiv.air_tickets.core.services.flight.FlightRepository;
import com.garryiv.air_tickets.core.services.flight.FlightServiceImplTest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.garryiv.air_tickets.core.services.reservation.ReservationServiceImpl.EARLIEST_CHECK_IN;
import static com.garryiv.air_tickets.core.services.reservation.ReservationServiceImpl.LATEST_CHECK_IN;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@CoreServiceTest
public class ReservationServiceImplTest {

    // The first flight in data.sql
    public static final Long TEST_FLIGHT_ID = 1L;

    public static final BigDecimal TEST_FLIGHT_PRICE = new BigDecimal(333);
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void create() throws Exception {
        UserInfo userInfo = userService.findOrCreate("test@email");
        ReservationInfo reservation = createReservation(userInfo);
        checkNewReservation(userInfo, reservation);
    }

    @Test
    public void findUserReservations() throws Exception {
        UserInfo userInfo = userService.findOrCreate(UUID.randomUUID().toString() + "@email");

        // no reservations at the beginning
        List<ReservationInfo> reservations = reservationService.findUserReservations(userInfo.getId());
        assertNotNull(reservations);
        assertEquals(0, reservations.size());

        // create one
        createReservation(userInfo);

        // make sure it found
        reservations = reservationService.findUserReservations(userInfo.getId());
        assertNotNull(reservations);
        assertEquals(1, reservations.size());
        checkNewReservation(userInfo, reservations.get(0));

    }

    @Test
    public void findUserReservation() throws Exception {
        UserInfo userInfo1 = userService.findOrCreate("test1@email");
        ReservationInfo reservation = createReservation(userInfo1);
        ReservationInfo found = reservationService.findUserReservation(userInfo1.getId(), reservation.getId());
        checkNewReservation(userInfo1, found);

        UserInfo userInfo2 = userService.findOrCreate("test2@email");
        try {
            reservationService.findUserReservation(userInfo2.getId(), reservation.getId());
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void findUserReservationNull1() throws Exception {
        reservationService.findUserReservation(null, 1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findUserReservationNull2() throws Exception {
        reservationService.findUserReservation(1L, null);
    }

    @Test
    public void cancelUserReservation() throws Exception {
        UserInfo userInfo = userService.findOrCreate("test@email");
        ReservationInfo reservation = createReservation(userInfo);
        reservationService.cancelUserReservation(userInfo.getId(), reservation.getId());

        // subsequent call does nothing
        reservationService.cancelUserReservation(userInfo.getId(), reservation.getId());
    }

    @Test
    public void findReservationsForCheckIn() throws Exception {
        UserInfo userInfo = userService.findOrCreate("test@email");

        // departure in 47 hours
        Date departure1 = DateUtils.addHours(new Date(), EARLIEST_CHECK_IN - 1);

        // departure in 49 hours
        Date departure2 = DateUtils.addHours(new Date(), EARLIEST_CHECK_IN + 1);

        Flight flight1 = createFlight(departure1);
        Flight flight2 = createFlight(departure2);

        ReservationInfo reservation1 = createReservation(userInfo, flight1.getId());
        setStatus(reservation1, ReservationStatus.PAID);
        ReservationInfo reservation2 = createReservation(userInfo, flight2.getId());
        setStatus(reservation2, ReservationStatus.PAID);

        Date from = DateUtils.addHours(new Date(), LATEST_CHECK_IN + 1);
        Set<Long> foundIds = reservationService.findReservationsForCheckIn(from)
                .stream()
                .map(ReservationInfo::getId)
                .collect(Collectors.toSet());

        assertThat(foundIds, hasItem(reservation1.getId()));
        assertThat(foundIds, not(hasItem(reservation2.getId())));
    }

    private void setStatus(ReservationInfo info, ReservationStatus status) {
        Reservation reservation = reservationRepository.findOne(info.getId());
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }

    private Flight createFlight(Date departure) {
        Flight flight = FlightServiceImplTest.newTestFlight();
        flight.setDeparture(departure);
        flight.setArrival(DateUtils.addHours(departure, 1));
        flightRepository.save(flight);
        return flight;
    }

    @Test
    public void handleFlightCancelledEvent() throws Exception {

    }

    private void checkNewReservation(UserInfo userInfo, ReservationInfo reservation) {
        assertNotNull(reservation);
        assertNotNull(reservation.getId());
        assertEquals(userInfo.getId(), reservation.getUserId());
        assertEquals(TEST_FLIGHT_ID, reservation.getFlightId());
        assertTrue(TEST_FLIGHT_PRICE.compareTo(reservation.getPrice()) == 0);
        assertEquals(ReservationStatus.CREATED, reservation.getStatus());
    }

    private ReservationInfo createReservation(UserInfo userInfo) {
        return createReservation(userInfo, TEST_FLIGHT_ID);
    }

    private ReservationInfo createReservation(UserInfo userInfo, Long flightId) {
        ReservationRequest request = new ReservationRequest();
        request.setFlightId(flightId);
        request.setUserId(userInfo.getId());

        return reservationService.create(request);
    }
}