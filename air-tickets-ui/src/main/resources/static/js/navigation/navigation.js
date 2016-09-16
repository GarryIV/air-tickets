angular.module('navigation', ['ngRoute']).controller('navigation', function($route) {

    var controller = {
    };

    controller.tab = function(route) {
        return $route.current && route === $route.current.controller;
    };

    return controller;
});