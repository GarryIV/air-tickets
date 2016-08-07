package com.garryiv.air_tickets.ui.controllers;

import com.garryiv.air_tickets.api.flight.FlightSearch;
import com.garryiv.air_tickets.api.flight.FlightService;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightControllerTest {
    @Test
    public void findAll() throws Exception {
        // Very basic test using Mockito, need to think about more complex use cases
        FlightService flightService = mock(FlightService.class);
        when(flightService.findAll(any(FlightSearch.class))).thenReturn(new ArrayList<>());
        FlightController controller = new FlightController(flightService);
        assertNotNull(controller.findAll(new FlightSearch()));
    }

}