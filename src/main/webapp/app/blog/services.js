(function (angular) {

    'use strict';

    var app = angular.module('ngBlogApp');

    app.factory('MessageFactory', function ($resource) {
        var mainUrl = 'api/message';
        return $resource(mainUrl + "/:id", {id: '@id'}, {
            save: {method: 'POST', url: mainUrl},
            update: {method: 'PUT', url: mainUrl},
            delete: {method: 'DELETE'},
            findAll: {method: 'GET', url: mainUrl + '/all', isArray: true},
            findAllByUserId: {method: 'GET', url: mainUrl + '/all/:userId', isArray: true}
        });
    });

})(window.angular);