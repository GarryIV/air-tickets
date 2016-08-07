angular.module('flight', []).controller('flight', function($http) {
    var controller = {
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

    controller.search = function () {
        var filter = controller.filter;
        var from = new Date(filter.departure.setHours(0,0,0,0));
        var to = addDays(from, 1);

        var query = {
            origin: filter.origin,
            destination: filter.destination,
            departureFrom: from,
            departureTo: to
        };
        $http.post('/api/flight/search', query).then(function (response) {
            controller.query = query;
            controller.flights = response.data;
        })
    };

    controller.cancelFlight = function (flight) {
        console.log("Cancel the flight " + flight);
    };

    return controller;
});
