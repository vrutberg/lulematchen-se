var appModule = angular.module('app', ['ngRoute']);

appModule.config(function($routeProvider) {
  $routeProvider.when('/', {
    templateUrl: 'views/game-list/game-list-template.html'
  });
});
