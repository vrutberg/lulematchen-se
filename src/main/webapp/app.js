var appModule = angular.module('app', ['ngRoute']);

appModule.config(function($routeProvider) {
  $routeProvider.when('/', {
    templateUrl: 'views/next-game/next-game-template.html'
  });
});
