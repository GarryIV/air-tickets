angular.module('reservation', []).controller('reservationNew', function($http,  $routeParams) {
    var reservation = {};

    $http.get('/api/flight/' + $routeParams.flightId).then(function (response) {
        reservation.flight = response.data;
    });

    return reservation;
});
