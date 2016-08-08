package com.garryiv.air_tickets.core.services.flight;

import com.garryiv.air_tickets.api.flight.FlightInfo;
import org.springframework.context.ApplicationEvent;

public class FlightCancelledEvent extends ApplicationEvent {
    private FlightInfo flight;

    public FlightCancelledEvent(FlightInfo flight) {
        super(flight);
        this.flight = flight;
    }

    public FlightInfo getFlight() {
        return flight;
    }
}
