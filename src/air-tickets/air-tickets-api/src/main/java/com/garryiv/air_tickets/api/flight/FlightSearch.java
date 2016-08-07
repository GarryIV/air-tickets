package com.garryiv.air_tickets.api.flight;

import java.util.Date;

public class FlightSearch {
    private String origin;
    private String destination;
    private Date departureFrom;
    private Date departureTo;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(Date departureFrom) {
        this.departureFrom = departureFrom;
    }

    public Date getDepartureTo() {
        return departureTo;
    }

    public void setDepartureTo(Date departureTo) {
        this.departureTo = departureTo;
    }
}
