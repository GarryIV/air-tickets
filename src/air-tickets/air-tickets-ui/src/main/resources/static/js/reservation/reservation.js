angular.module('reservation', []).controller('reservation', function($http,  $routeParams) {
    var reservation = {};
    reservation.name = 'reservation';
    reservation.flightId = $routeParams.flightId
    return reservation;
});
