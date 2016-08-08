package com.garryiv.air_tickets.core.services.flight;

import com.garryiv.air_tickets.api.flight.FlightSearch;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
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
     * Filter flight from the range
     * @param from departure after specified date
     * @param to departure before specified date
     * @return spec
     */
    public static Specification<Flight> departureBetween(Date from, Date to) {
        return (root, query, cb) -> cb.and(
                cb.greaterThanOrEqualTo(root.get("departure"), from),
                cb.lessThan(root.get("departure"), to)
        );
    }

    /**
     * Find flights with specified status
     * @param status status to find
     * @return spec
     */
     public static Specification<Flight> statusEquals(FlightStatus status) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get("status"), status)
        );
    }


    /**
     * Filter by origin, destination and departure day
     * @param search search parameters
     * @return spec
     */
    public static Specification<Flight> searchScheduled(FlightSearch search) {
        return where(originEquals(search.getOrigin()))
                .and(destinationEquals(search.getDestination()))
                .and(departureBetween(search.getDepartureFrom(), search.getDepartureTo()))
                .and(statusEquals(FlightStatus.SCHEDULED));
    }

    private static Predicate equalIgnoreCase(CriteriaBuilder cb, Expression<String> x, String y) {
        return cb.equal(x, y.toUpperCase());
    }
}
