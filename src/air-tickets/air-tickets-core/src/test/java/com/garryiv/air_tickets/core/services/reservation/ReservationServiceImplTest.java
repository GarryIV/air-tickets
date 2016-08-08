package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.reservation.ReservationRequest;
import com.garryiv.air_tickets.api.reservation.ReservationService;
import com.garryiv.air_tickets.api.reservation.ReservationStatus;
import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import com.garryiv.air_tickets.core.services.CoreSpringBootTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@CoreSpringBootTest
public class ReservationServiceImplTest {

    // The first flight in data.sql
    public static final Long TEST_FLIGHT_ID = 1L;

    public static final BigDecimal TEST_FLIGHT_PRICE = new BigDecimal(333);
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

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
        ReservationRequest request = new ReservationRequest();
        request.setFlightId(TEST_FLIGHT_ID);
        request.setUserId(userInfo.getId());

        return reservationService.create(request);
    }
}