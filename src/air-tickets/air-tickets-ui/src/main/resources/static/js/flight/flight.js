angular.module('flight', []).controller('flight', function($http) {
    var flight = {
        filter: {
            from: 'CDG',
            to: 'JFK',
            departure: new Date()
        },
        search: function () {

        }
    };

    return flight;
});
