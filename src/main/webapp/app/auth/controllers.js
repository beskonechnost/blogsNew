(function (angular) {

    'use strict';

    var app = angular.module('ngBlogApp');

    app.controller('AuthCtrl', ['$scope', '$location', '$rootScope', '$http', 'AuthService', '$window',
        function ($scope, $location, $rootScope, $http, AuthService, $window) {

            $scope.credentials = {};
            $scope.errorMessage = "There was a problem logging in. Please try again";

            $scope.goHistoryBack = function () {
                $window.history.back();
            };

            $scope.init = function () {
                $scope.reLogin = false;
                if ($location.$$path === '/logout') {
                    $scope.logout();
                } else if ($location.$$path === '/login') {
                    $scope.error = true;
                    $scope.reLogin = true;
                    $scope.errorMessage = "There was a problem logging in. Please try again ";
                }
            };

            $scope.authenticate = function (credentials, callback) {
                AuthService.login(credentials).then(function (response) {
                    if (response.status === 200) {
                        AuthService.setAuthenticatedUser(response.data);
                    }
                    callback();
                }, function (response) {
                    if (response.data && response.data.message) {
                        $scope.errorMessage = response.data.message;
                    }
                    $rootScope.authenticated = false;
                    callback();
                });
            };

            $scope.login = function () {
                $scope.authenticate($scope.credentials, function () {
                    if ($rootScope.authenticated) {
                        $location.path("/blog");
                        $scope.error = false;
                    } else {
                        $location.path("/login");
                        $scope.error = true;
                    }
                });
            };

            $scope.logout = function () {
                AuthService.logout();
            };

            $scope.init();
        }]);
})(window.angular);