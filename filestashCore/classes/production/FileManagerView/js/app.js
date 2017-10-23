'use strict';

/*angular.module('myApp', [
    'myApp.controllers','apartmentModule'
]);*/
// Declare app level module which depends on filters, and services
angular.module('myApp', [
  'ngRoute',
    'ui.grid',
  //'myApp.filters',
  'myApp.services',
  'myApp.directives',
    'loginModule',
  'myApp.controllers',
    'apartmentModule',
    'fileModule'

]).
config(['$routeProvider','$locationProvider', function($routeProvider,$locationProvider) {
  $routeProvider.when('/view1', {controller: 'MyCtrl1',
      templateUrl: 'partials/partial1.html' });
  $routeProvider.when('/view2/:firstname/:lastname', {controller: 'MyCtrl2',
      templateUrl: 'partials/partial2.html'});
  //$routeProvider.otherwise({redirectTo: '/view1'});
  //$locationProvider.html5Mode(true);
}]);
