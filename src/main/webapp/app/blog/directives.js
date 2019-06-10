(function (angular) {

    'use strict';

    var app = angular.module("ngBlogApp");

    app.directive('messageItem', function () {
        return {
            scope: {
                messageItem: '=',
                index: '='
            },
            restrict: 'E',
            templateUrl: 'app/blog/views/message-item.html',
            controller: 'MessageCtrl'
        };
    });

})(window.angular);