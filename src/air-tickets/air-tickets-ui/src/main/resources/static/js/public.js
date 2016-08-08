angular
		// .module('public', [ 'ngRoute', 'auth', 'navigation'])
		.module('public', [ 'ngRoute', 'auth', 'home', 'flight', 'reservation', 'navigation' ])
		.config(

				function($routeProvider, $httpProvider, $locationProvider) {

					$locationProvider.html5Mode(true);

					$routeProvider.when('/', {
						templateUrl : 'js/home/home.html',
						controller : 'home',
						controllerAs : 'controller'
					}).when('/flight', {
						templateUrl : 'js/flight/flight.html',
						controller : 'flight',
						controllerAs : 'controller'
					}).when('/user/reservation/create/:flightId', {
						templateUrl : 'js/reservation/reservation-new.html',
						controller : 'reservationNew',
						controllerAs : 'controller'
					}).when('/user/reservation', {
						templateUrl : 'js/reservation/reservations.html',
						controller : 'reservationMy',
						controllerAs : 'controller'
					}).when('/staff/reservation', {
						templateUrl : 'js/reservation/reservations.html',
						controller : 'reservationStaff',
						controllerAs : 'controller'
					}).otherwise('/');

					$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

				}).run(function(auth) {
		});
