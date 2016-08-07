angular.module('home', []).controller('home', function($http, auth) {
    return {
        username: function () {
            return auth.credentials.username;
        }
    };
});
