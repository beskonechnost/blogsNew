(function (angular) {

    'use strict';

    var app = angular.module('ngBlogApp');

    app.controller('BlogCtrl', ['$scope', '$rootScope', '$location', '$route', '$routeParams', 'MessageFactory', 'AuthService',
        function ($scope, $rootScope, $location, $route, $routeParams, MessageFactory, AuthService) {

            $scope.newMessage = true;
            $scope.onlyMyMessage = false;
            $scope.blog = [];
            $scope.message = {};

            $scope.init = function () {
                if($scope.newMessage) {
                    MessageFactory.findAll(function (resp) {
                        $scope.blog = resp;
                    });
                } else {
                    MessageFactory.findAllByUserId({userId: $rootScope.principal.id}, function (resp) {
                        $scope.blog = resp;
                    })
                }
            };

            $scope.updateNewMessage = function () {
                $scope.newMessage = false;
            };

            $scope.onSendOrEditButtonClick = function () {
                $scope.spinner = true;
                var saveFunction = $scope.newMessage ? MessageFactory.save : MessageFactory.update;
                saveFunction($scope.message, function (resp) {
                    if($scope.newMessage) {
                        $scope.blog.push(resp);
                        $scope.message = {};
                        $scope.spinner = false;
                    } else {
                        for(var i = 0; i < $scope.blog.length; i++) {
                            if($scope.blog[i].id === resp.id) {
                                $scope.blog[i].messageText = resp.messageText;
                                break;
                            }
                            $scope.message = {};
                            $scope.newMessage = true;
                            $scope.spinner = false;
                        }
                    }
                });
            };

            $scope.onDeleteMessage = function (message, index) {
                BootstrapDialog.show({
                    title: 'Confirm',
                    closable: false,
                    closeByBackdrop: false,
                    message: 'Are you sure? The message will be deleted forever.',
                    type: BootstrapDialog.TYPE_WARNING,
                    buttons: [
                        {
                            label: 'Yes',
                            hotkey: 13,
                            action: function (dialogRef) {
                                MessageFactory.delete(message, function (resp) {
                                    $scope.blog.splice(index, 1);
                                });
                                dialogRef.close();
                            }
                        },
                        {
                            label: 'Cancel',
                            hotkey: 27,
                            action: function (dialogRef) {
                                dialogRef.close();
                            }
                        }]
                });
            };

            $scope.showOnlyMyMessageOrAll = function () {
                $scope.newMessage = !$scope.newMessage;
                $scope.init();
            };

            $scope.onLogoutClick = function () {
                AuthService.logout();
            };

            $scope.init();
    }]);

    app.controller('MessageCtrl', ['$scope', '$rootScope', function ($scope, $rootScope) {

        $scope.thisIsCurrentUserMessage = function (userId) {
            return userId === $rootScope.principal.id;
        };

        $scope.onEditMessage = function (message) {
            $scope.$parent.updateNewMessage();
            $scope.$parent.message.messageText = message.messageText;
            $scope.$parent.message.id = message.id;
        };

        $scope.onDeleteMessage = function (message, index) {
            $scope.$parent.onDeleteMessage(message, index);
        }

    }]);

})(window.angular);