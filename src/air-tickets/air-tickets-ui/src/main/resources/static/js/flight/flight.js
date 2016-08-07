angular.module('flight', []).controller('flight', function($http) {
    var flight = {
        filter: {
            from: 'CDG',
            to: 'JFK'
        },
        search: function () {

        }
    };

    return flight;
});
