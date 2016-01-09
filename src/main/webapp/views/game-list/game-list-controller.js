angular.module('app').controller('GameListController', ['ApiService',
  function(ApiService) {
    var vm = this;

    vm.games = [];

    vm.getGames = function() {
      ApiService.getGames().then(function(response) {
        vm.games = response.data.games;
      });
    };

    vm.text = 'snickers är gott som fan';
}]);