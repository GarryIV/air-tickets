package com.garryiv.air_tickets.api.reservation;

import lombok.ToString;

@ToString
public class ReservationRequest {
    private Long flightId;
    private Long userId;

    public ReservationRequest() {
    }

    public ReservationRequest(Long flightId, Long userId) {
        this.flightId = flightId;
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
