'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', [])
    .service ('RestService', function($http) {
        this.baseUrl = 'http://localhost:8080/filemanager/';

        this.post = function (url, data, params) {
            var self = this;

            var params = params;

            if (!params) {
                params = {};
            }

            return $http({
                method: 'POST',
                url: self.baseUrl + url,
                data: data,
                params: params
            }).then(function (res) {
                return res.data;
            });
        };

        this.get = function (url, params) {
            var self = this;

            return $http({
                method: 'GET',
                url: self.baseUrl + url,
                params: params
            }).then(function (res) {
                return res.data;
            })
        };

        this.getwithouparams = function (url) {
            var self = this;

            return $http({
                method: 'GET',
                url: self.baseUrl + url,
                //params: params
            }).then(function (res) {
                return res.data;
            })
        };
    })

    .service('helloService',function($http){
     this.GetApiCall=function (dataReceived) {
       return $http.get('http://localhost:8080/FirstSpringMVCProject/welcome');

     }
})
    .value('version', '0.1');
