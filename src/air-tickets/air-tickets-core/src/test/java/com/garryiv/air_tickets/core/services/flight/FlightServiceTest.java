package com.garryiv.air_tickets.core.services.flight;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
        Flight exampleFlight = new Flight();
        exampleFlight.setFlightNumber("YY333");
        exampleFlight.setDeparture(new Date());
        exampleFlight.setArrival(DateUtils.addHours(exampleFlight.getDeparture(), 3));
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