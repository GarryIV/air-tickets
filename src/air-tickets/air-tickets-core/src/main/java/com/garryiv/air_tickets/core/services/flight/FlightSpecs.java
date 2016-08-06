package com.garryiv.air_tickets.core.services.flight;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Specification for the flight
 */
public class FlightSpecs {

    /**
     * Filter by origin case insensitive.
     * @param origin example origin
     * @return spec
     */
    public static Specification<Flight> originEquals(String origin) {
        return (root, query, cb) -> equalIgnoreCase(cb, cb.upper(root.get("origin")), origin);
    }

    /**
     * Filter by destination case insensitive.
     * @param destination example destination
     * @return spec
     */
    public static Specification<Flight> destinationEquals(String destination) {
        return (root, query, cb) -> equalIgnoreCase(cb, cb.upper(root.get("destination")), destination);
    }

    /**
     * Filter flight from the same day
     * @param departure example departure
     * @return spec
     */
    public static Specification<Flight> departureEquals(LocalDate departure) {
        return (root, query, cb) -> cb.and(
                cb.greaterThanOrEqualTo(root.get("departure"), departure.atStartOfDay()),
                cb.lessThan(root.get("departure"), departure.plusDays(1).atStartOfDay())
        );
    }

    /**
     * Filter by origin, destination and departure day
     * @param flight example flight
     * @return spec
     */
    public static Specification<Flight> from(Flight flight) {
        return where(originEquals(flight.getOrigin()))
                .or(destinationEquals(flight.getDestination()))
                .or(departureEquals(flight.getDeparture().toLocalDate()));
    }

    private static Predicate equalIgnoreCase(CriteriaBuilder cb, Expression<String> x, String y) {
        return cb.equal(x, y.toUpperCase());
    }
}
