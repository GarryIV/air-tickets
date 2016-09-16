package com.garryiv.air_tickets.core.flight;

import com.garryiv.air_tickets.api.flight.FlightInfo;
import com.garryiv.air_tickets.api.flight.FlightSearch;
import com.garryiv.air_tickets.api.flight.FlightService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, ApplicationEventPublisher eventPublisher) {
        this.flightRepository = flightRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<FlightInfo> searchScheduled(@RequestBody FlightSearch search) {
        Assert.notNull(search, "search");

        return flightRepository.findAll(FlightSpecs.searchScheduled(search))
                .map(flight -> toFlightInfo(flight))
                .collect(Collectors.toList());
    }

    @Override
    public FlightInfo find(@PathVariable Long flightId) {
        Assert.notNull(flightId, "flightId");
        return toFlightInfo(flightRepository.findOne(flightId));
    }

    @Override
    public void cancel(@PathVariable Long flightId) {
        Assert.notNull(flightId, "flightId");
        Flight flight = flightRepository.findOne(flightId);
        if(flight.getStatus() == FlightStatus.CANCELLED) {
            // already cancelled
            return;
        }
        flight.setStatus(FlightStatus.CANCELLED);
        flightRepository.save(flight);
        eventPublisher.publishEvent(new FlightCancelledEvent(toFlightInfo(flight)));
    }

    private FlightInfo toFlightInfo(Flight flight) {
        Assert.notNull(flight, "flight");

        FlightInfo flightInfo = new FlightInfo();
        BeanUtils.copyProperties(flight, flightInfo);
        return flightInfo;
    }
}
