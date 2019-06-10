var RESOURCES_PATH = "app";

(function (angular) {

    'use strict';

    angular.module('ngBlogApp', ['ngRoute', 'ngResource', 'ngCookies', 'ngSanitize', 'ui.bootstrap'])

        .factory('errorHandler', ['$q', '$rootScope', '$location', function ($q, $rootScope, $location) {
            return {
                responseError: function (response) {
                    if (!$rootScope.authenticated) {
                        $location.path('/login');
                    }
                    return $q.reject(response);
                }
            }
        }])
        .config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {

            $routeProvider
                .when('/blog', {
                    templateUrl: 'app/blog/views/blog.html',
                    controller: 'BlogCtrl'
                })
                .when('/login', {
                    templateUrl: 'app/auth/login.html',
                    controller: 'AuthCtrl'
                })
                .when('/logout', {
                    templateUrl: 'app/auth/login.html',
                    controller: 'AuthCtrl'
                })
                .otherwise({
                    redirectTo: '/login'
                });
             $httpProvider.interceptors.push('errorHandler');
        }])
        .run(['$rootScope', '$templateCache', 'AuthService', function ($rootScope, $templateCache, AuthService) {

            // Clear browser cache (in development mode)
            // http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
            $rootScope.$on('$viewContentLoaded', function () {
                $templateCache.removeAll();
            });

            $rootScope.$on("$routeChangeStart", function (event, next, current) {
                // LeavePageService.disableSafeMode();
                AuthService.checkAccessForPath(next.originalPath);
            });

        }]);

})(window.angular);