angular.module('flight', []).controller('flight', function($http) {
    var flight = {
        filter: {
            from: 'CDG',
            to: 'JFK',
            departure: new Date()
        },
        flights: null
    };

    flight.search = function () {
        $http.post('/api/flight/search', flight.filter).then(function () {
            flight = response.data;
        })
    };

    return flight;
});
