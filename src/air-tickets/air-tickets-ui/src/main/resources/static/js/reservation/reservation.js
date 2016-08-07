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

    $http.get('/api/my-reservation/' + $routeParams.id).then(function (response) {
        reservation.reservation = response.data;
    });

    return reservation;
}).controller('reservationMy', function($http) {
    var reservation = {};

    $http.get('api/my-reservation').then(function (response) {
        reservation.reservations = response.data;
    });

    return reservation;
});
