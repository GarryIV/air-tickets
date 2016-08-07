package com.garryiv.air_tickets.core.services.flight;

import com.garryiv.air_tickets.api.flight.FlightInfo;
import com.garryiv.air_tickets.api.flight.FlightSearch;
import com.garryiv.air_tickets.api.flight.FlightService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<FlightInfo> findAll(@RequestBody FlightSearch search) {
        return flightRepository.findAll(FlightSpecs.from(search))
                .map(flight -> toFlightInfo(flight))
                .collect(Collectors.toList());
    }

    private FlightInfo toFlightInfo(Flight flight) {
        FlightInfo flightInfo = new FlightInfo();
        BeanUtils.copyProperties(flight, flightInfo);
        return flightInfo;
    }
}
