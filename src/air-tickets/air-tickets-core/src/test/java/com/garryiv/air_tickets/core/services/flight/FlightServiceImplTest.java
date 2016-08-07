package com.garryiv.air_tickets.core.services.flight;

import com.garryiv.air_tickets.api.flight.FlightInfo;
import com.garryiv.air_tickets.api.flight.FlightSearch;
import com.garryiv.air_tickets.api.flight.FlightService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlightServiceImplTest {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void findAll() {
        Flight exampleFlight = new Flight();
        exampleFlight.setFlightNumber("YY333");
        exampleFlight.setDeparture(DateUtils.truncate(new Date(), Calendar.MINUTE));
        exampleFlight.setArrival(DateUtils.addHours(exampleFlight.getDeparture(), 3));
        exampleFlight.setOrigin("DMD");
        exampleFlight.setDestination("SDG");
        exampleFlight.setPrice(new BigDecimal(10));
        exampleFlight.setStatus(FlightStatus.SCHEDULED);
        flightRepository.save(exampleFlight);
        exampleFlight = flightRepository.findOne(exampleFlight.getId());

        FlightSearch search = new FlightSearch();
        search.setOrigin(exampleFlight.getOrigin());
        search.setDestination(exampleFlight.getDestination());
        search.setDepartureFrom(DateUtils.addDays(exampleFlight.getDeparture(), -1));
        search.setDepartureTo(DateUtils.addDays(exampleFlight.getDeparture(), 1));

        List<FlightInfo> flights = flightService.findAll(search);
        assertNotNull(flights);
        assertEquals(1, flights.size());
        FlightInfo first = flights.get(0);
        assertEquals(exampleFlight.getId(), first.getId());
        assertEquals(exampleFlight.getOrigin(), first.getOrigin());
        assertEquals(exampleFlight.getDestination(), first.getDestination());
        assertEquals(exampleFlight.getDeparture(), first.getDeparture());
        assertEquals(exampleFlight.getArrival(), first.getArrival());
        assertEquals(exampleFlight.getFlightNumber(), first.getFlightNumber());
        assertTrue(exampleFlight.getPrice().compareTo(first.getPrice()) == 0);
    }

}