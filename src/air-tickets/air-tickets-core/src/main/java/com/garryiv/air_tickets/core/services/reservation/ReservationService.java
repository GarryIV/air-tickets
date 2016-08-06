package com.garryiv.air_tickets.core.services.reservation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationService {
    public List<Reservation> findCurrentReservations(Long userId) {
        return new ArrayList<>();
    }
}
