package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.reservation.ReservationRequest;
import com.garryiv.air_tickets.api.reservation.ReservationService;
import com.garryiv.air_tickets.api.reservation.ReservationStatus;
import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
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

        ReservationRequest request = new ReservationRequest();
        request.setFlightId(TEST_FLIGHT_ID);
        request.setUserId(userInfo.getId());

        ReservationInfo reservation = reservationService.create(request);

        assertNotNull(reservation);
        assertNotNull(reservation.getId());
        assertEquals(userInfo.getId(), reservation.getUserId());
        assertEquals(TEST_FLIGHT_ID, reservation.getFlightId());
        assertTrue(TEST_FLIGHT_PRICE.compareTo(reservation.getPrice()) == 0);
        assertEquals(ReservationStatus.CREATED, reservation.getStatus());

    }

}