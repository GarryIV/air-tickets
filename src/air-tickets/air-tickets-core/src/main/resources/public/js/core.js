angular.module("app", []).controller("home", function($rootScope, $http, $httpParamSerializer, $window) {
	var self = this;

    // check current authentication by getting user details
	var checkAuthentication = function() {
		$http.get('/user').then(function(response) {
		    if (response.data.name) {
                self.credentials.username = response.data.name;
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
		}, function() {
			$rootScope.authenticated = false;
		});
	}

    self.credentials = {};
    checkAuthentication();

    // perform login for staff
    self.loginStaff = function() {
        $http({
            url: '/login/staff',
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: $httpParamSerializer(self.credentials)
        }).then(function (response) {
            if (response.data.redirect_uri) {
                $window.location = response.data.redirect_uri;
            }
            self.error = false;
            $rootScope.authenticated = true;
        }, function () {
            self.error = true;
            $rootScope.authenticated = false;
        });
    };

    self.logout = function() {
        $http.post('logout', {}).finally(function() {
            $rootScope.authenticated = false;
        });
    }
});