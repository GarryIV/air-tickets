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

        $http.post('/api/flight/search', {
            origin: filter.origin,
            destination: filter.destination,
            departureFrom: from,
            departureTo: to
        }).then(function (response) {
            flight.flights = response.data;
        })
    };

    return flight;
});
