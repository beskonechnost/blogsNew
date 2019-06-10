(function (angular) {

    'use strict';

    var App = angular.module('ngBlogApp');

    App.service('AuthService', ['$http', '$rootScope', '$location',
        function ($http, $rootScope, $location) {

            this.currentUser = function () {
                if ($rootScope.authenticated) {
                    return $rootScope.principal.username
                }
            };

            this.login = function (credentials) {
                var headers =
                    {
                        Authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
                    };
                return $http.post('api/login', null, {headers: headers});
            };

            this.logout = function () {
                $http.post('logout').then(function () {
                    console.info("Spring logout success");

                    $rootScope.authenticated = false;
                    $location.path("/login");

                });
            };

            this.setAuthenticatedUser = function (jsonObject) {
                console.debug("load user details", jsonObject);
                $rootScope.authenticated = true;
                $rootScope.principal = {};
                $rootScope.principal.id = jsonObject.id;
                $rootScope.principal.username = jsonObject.username;
                //todo just for debugging
                window.FS_USER = $rootScope.principal;
            };


            this.checkAccessForPath = function (path) {
                if (!$rootScope.authenticated && path !== '/login') {
                    var me = this;
                    $http.post('api/login').then(function (response) {
                        me.setAuthenticatedUser(response.data);
                    }, function () {
                        if (path === '/login' || path === '/logout') {
                            // already going to #login, no redirect needed
                        } else {
                            // not going to #login, we should redirect now
                            $location.path('/login');
                        }
                        $rootScope.authenticated = false;
                    });
                }
            };

        }]);

})(window.angular);