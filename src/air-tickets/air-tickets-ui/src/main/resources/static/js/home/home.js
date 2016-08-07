angular.module('home', ['auth']).controller('home', function($http, auth) {
    return {
        username: function () {
            return auth.credentials.username;
        },
        authenticated: function () {
            return auth.authenticated;
        },
        loginUser: auth.loginUser,
        logout: auth.logout,
        auth: function () {
            return auth;
        }
    };
});
