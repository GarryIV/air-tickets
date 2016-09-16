package com.garryiv.air_tickets.api.reservation;

public enum ReservationStatus {
    /**
     * Just created reservation
     */
    CREATED,

    /**
     * Reservation is paid
     */
    PAID,

    /**
     * User checked-in
     */
    CHECKED_IN,

    /**
     * Reservation was cancelled
     */
    CANCELLED
}
