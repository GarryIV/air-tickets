package com.garryiv.air_tickets.core.services.flight;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Calendar;
import java.util.Date;

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
    public static Specification<Flight> departureEquals(Date departure) {
        Date from = DateUtils.round(departure, Calendar.DAY_OF_MONTH);
        Date to = DateUtils.addDays(from, 1);
        return (root, query, cb) -> cb.and(
                cb.greaterThanOrEqualTo(root.get("departure"), from),
                cb.lessThan(root.get("departure"), to)
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
                .or(departureEquals(flight.getDeparture()));
    }

    private static Predicate equalIgnoreCase(CriteriaBuilder cb, Expression<String> x, String y) {
        return cb.equal(x, y.toUpperCase());
    }
}
