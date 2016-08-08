angular.module('auth', []).factory('auth', function($http, $httpParamSerializer, $window, $rootScope) {

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
                auth.error = false;
                auth.authenticated = true;
                auth.credentials = response.data;
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
        },

        hasRole: function(role) {
            if (!auth.authenticated) {
                return false;
            }

            try {
                return auth.credentials.authorities.indexOf(role) >= 0;
            } catch (e) {
                return false;
            }
        },

        isStaff: function () {
            return auth.hasRole('ROLE_ADMIN')
        },

        isPublic: function () {
            return auth.hasRole('ROLE_PUBLIC')
        }
    };

    // check current authentication by getting user details
    var checkAuthentication = function() {
        $http.get('/user').then(function(response) {
            if (response.data.username) {
                auth.credentials = response.data;
                auth.authenticated = true;
            } else {
                auth.authenticated = false;
            }
        }, function() {
            auth.authenticated = false;
        });
    }

    checkAuthentication();

    $rootScope.auth = auth;

    return auth;
});
