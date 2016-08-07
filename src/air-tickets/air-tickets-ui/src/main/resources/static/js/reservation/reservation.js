angular.module('reservation', []).controller('reservationNew', function($http,  $routeParams, $location) {
    var reservation = {};

    $http.get('/api/flight/' + $routeParams.flightId).then(function (response) {
        reservation.flight = response.data;
    });

    reservation.reserve = function () {
        $http.put('/api/my-reservation/flight/' + reservation.flight.id).then(function (response) {
            $location.path('/user/reservation/' + response.data.id);
        });
    };

    return reservation;

}).controller('reservationView', function($http,  $routeParams, $location) {
    var reservation = {};


    return reservation;
});
