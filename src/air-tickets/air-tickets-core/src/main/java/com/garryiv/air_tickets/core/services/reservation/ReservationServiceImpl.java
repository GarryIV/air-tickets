package com.garryiv.air_tickets.core.services.reservation;

import com.garryiv.air_tickets.api.reservation.ReservationInfo;
import com.garryiv.air_tickets.api.reservation.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {
    @Override
    public List<ReservationInfo> findCurrentReservations(Long userId) {
        return new ArrayList<>();
    }
}
