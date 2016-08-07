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

}).controller('reservationView', function($http,  $routeParams, $location) {
    var controller = {};

    $http.get('/api/my-reservation/' + $routeParams.id).then(function (response) {
        controller.reservation = response.data;
    });

    return controller;
}).controller('reservationMy', function($http) {
    var controller = {};

    $http.get('api/my-reservation').then(function (response) {
        controller.reservations = response.data;
    });

    controller.pay = function (reservation) {

    };

    controller.cancel = function (reservation) {

    };

    return controller;
});
