package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationStatus;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@Entity
@Table(name = "RESERVATION_TBL")
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue
    private Long id;

    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "user_id")
    private Long userId;

    private String origin;

    private String destination;

    private Date departure;

    private Date arrival;

    private String flightNumber;

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

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
