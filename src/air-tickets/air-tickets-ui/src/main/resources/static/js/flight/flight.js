angular.module('flight', []).controller('flight', function($http) {
    var flight = {
        filter: {
            origin: 'CDG',
            destination: 'JFK',
            departure: new Date()
        },
        flights: null
    };

    var addDays = function(date, days) {
        var result = new Date(date);
        result.setDate(result.getDate() + days);
        return result;
    }

    flight.search = function () {
        var filter = flight.filter;
        var from = new Date(filter.departure.setHours(0,0,0,0));
        var to = addDays(from, 1);

        var query = {
            origin: filter.origin,
            destination: filter.destination,
            departureFrom: from,
            departureTo: to
        };
        $http.post('/api/flight/search', query).then(function (response) {
            flight.query = query;
            flight.flights = response.data;
        })
    };

    flight.reserveTicket = function (flight) {
        console.log("Reserve a ticket " + flight);
    };

    flight.cancelFlight = function (flight) {
        console.log("Cancel the flight " + flight);
    };

    return flight;
});
