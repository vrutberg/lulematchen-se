angular.module('app').controller('NextGameController', ['ApiService',
  function(ApiService) {
    var vm = this;

    vm.game = {};

    vm.getGame = function() {
      ApiService.getFirstUnplayedGame().then(function(response) {
        vm.game = response.data;
      });
    };
  }]);