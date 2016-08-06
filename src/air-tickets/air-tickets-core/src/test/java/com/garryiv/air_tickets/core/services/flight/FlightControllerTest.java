package com.garryiv.air_tickets.core.services.flight;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightControllerTest {

    @Test
    public void findAll() throws Exception {
        FlightService flightService = mock(FlightService.class);
        Page<Flight> page = new PageImpl<>(Collections.emptyList());
        when(flightService.findAll(any(Flight.class), any(Pageable.class))).thenReturn(page);

        FlightController controller = new FlightController(flightService);

        assertNotNull(controller.findAll(new Flight()));
    }

}