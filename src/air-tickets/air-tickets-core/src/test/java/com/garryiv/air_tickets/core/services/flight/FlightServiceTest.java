package com.garryiv.air_tickets.core.services.flight;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void findAll() {
        LocalDateTime now = LocalDateTime.now();

        Flight exampleFlight = new Flight();
        exampleFlight.setFlightNumber("YY333");
        exampleFlight.setDeparture(now);
        exampleFlight.setDeparture(now.plusHours(3));
        exampleFlight.setOrigin("DMD");
        exampleFlight.setDestination("SDG");
        flightRepository.save(exampleFlight);

        Page<Flight> flights = flightService.findAll(exampleFlight, new PageRequest(0, 10));
        assertNotNull(flights);
        assertEquals(1, flights.getTotalElements());
        Flight first = flights.getContent().get(0);
        assertNotNull(first.getId());
    }

}