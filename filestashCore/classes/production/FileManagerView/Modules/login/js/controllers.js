'use strict';

/* Controllers */

angular.module('myApp.login.controllers', [])


   .controller('LoginController', function($scope,$uibModal) {
        $scope.open=function(){
            console.log('opening pop up');
            var popUpInstance=$uibModal.open({
                templateUrl:'Modules/login/login.html',
                controller:'SubmitController'
            });
        }


    })

    .controller('SubmitController', function ($scope,$http,RestService) {
        $scope.user={};

        $scope.loginUser=function () {

            /*var data={
                "userID" : $scope.user.userID,
                "password" :$scope.user.passcode
            };*/
            return RestService.post('File/viewFilesForUser',$scope.user);

        }
        $http.get('http://localhost:8080/FirstSpringMVCProject/welcome')
            .then(function(response) {
                $scope.greeting = response.data;
                console.log('Response from server : ' + data)
            });

    });





