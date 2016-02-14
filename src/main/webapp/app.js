var appModule = angular.module('app', ['ngRoute']);

appModule.constant("Modernizr", Modernizr);

appModule.config(function($routeProvider) {
  $routeProvider.when('/', {
    templateUrl: 'views/main/main-template.html'
  });
});
