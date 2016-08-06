package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.core.services.EntitySupport;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RESERVATION_TBL")
public class Reservation extends EntitySupport {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue
    private Long id;

    @Column(name = "flight_id")
    @NotNull
    private Long flightId;

    @Column(name = "user_id")
    @NotNull
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
