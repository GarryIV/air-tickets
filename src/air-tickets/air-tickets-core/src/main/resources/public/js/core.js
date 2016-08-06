angular.module("app", []).controller("home", function($rootScope, $http) {
	var self = this;

	var authenticate = function(credentials, callback) {
		var headers = credentials ? {
			authorization : "Basic " + btoa(credentials.username + ":" + credentials.password),
            "X-Requested-With": "XMLHttpRequest"
		} : {};

		$http.get('/user', {
			headers : headers
		}).then(function(response) {

			if (response.data.name) {
                self.user = response.data.name
			    $rootScope.authenticated = true;
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback($rootScope.authenticated);
		}, function() {
			$rootScope.authenticated = false;
			callback && callback(false);
		});
	}

    authenticate();

    self.credentials = {};
    self.login = function() {
        authenticate(self.credentials, function(authenticated) { self.error = !authenticated; })
    };

    self.logout = function() {
        $http.post('logout', {}).finally(function() {
            $rootScope.authenticated = false;
        });
    }
});