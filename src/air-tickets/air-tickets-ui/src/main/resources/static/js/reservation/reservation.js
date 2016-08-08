angular.module('reservation', []).controller('reservationNew', function($http,  $routeParams, $location) {
    var controller = {};

    $http.get('/api/flight/' + $routeParams.flightId).then(function (response) {
        controller.flight = response.data;
    });

    controller.reserve = function () {
        $http.put('/api/my-reservation/flight/' + controller.flight.id).then(function (response) {
            $location.path('/user/reservation');
        });
    };

    return controller;

}).controller('reservationMy', function($http) {
    var controller = {};

    var refresh = function() {
        $http.get('api/my-reservation').then(function (response) {
            controller.reservations = response.data;
        });
    };

    refresh();

    controller.canPay = function (reservation) {
        return reservation.status == 'CREATED';
    };

    controller.pay = function (reservation) {

    };

    controller.cancel = function (reservation) {
        $http.delete('api/my-reservation/' + reservation.id).then(refresh);
    };

    return controller;
});
