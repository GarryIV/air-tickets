angular.module('auth', []).factory('auth', function($http, $httpParamSerializer, $window) {

    var auth = {
        name: 'auth',
        credentials: {},
        authenticated: false,

        // perform login for staff
        loginStaff: function() {
            $http({
                url: '/login/staff',
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                data: $httpParamSerializer(auth.credentials)
            }).then(function (response) {
                if (response.data.redirect_uri) {
                    $window.location = response.data.redirect_uri;
                }
                auth.error = false;
                auth.authenticated = true;
                auth.credentials.password = null;
            }, function () {
                auth.error = true;
                auth.authenticated = false;
            });
        },

        loginUser: function(provider) {
            $window.location = "/login/" + provider;
        },

        logout: function() {
            $http.post('logout', {}).finally(function() {
                auth.authenticated = false;
            });
        }
    };

    // check current authentication by getting user details
    var checkAuthentication = function() {
        $http.get('/user').then(function(response) {
            if (response.data.name) {
                auth.credentials.username = response.data.name;
                auth.authenticated = true;
            } else {
                auth.authenticated = false;
            }
        }, function() {
            auth.authenticated = false;
        });
    }

    checkAuthentication();

    return auth;
});
