package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationStatus;
import com.garryiv.air_tickets.core.services.EntitySupport;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RESERVATION_TBL")
public class Reservation extends EntitySupport {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue
    private Long id;

    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "user_id")
    private Long userId;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

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

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
