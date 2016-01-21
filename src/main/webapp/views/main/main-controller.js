angular.module('app').controller('MainController', ['ApiService', '$interval',
  function(ApiService, $interval) {
    var vm = this;

    vm.game = undefined;
    vm.hasGameStarted = false;

    vm.isBeforeNow = function(dateTimeString) {
      var nowMoment = moment();
      var gameTimeMoment = moment(dateTimeString);

      return gameTimeMoment.isBefore(nowMoment);
    };

    vm.getGame = function() {
      return ApiService.getTodaysGames().then(function(response) {
        if (response.data.length > 0) {
          return response.data[0];
        }

        return ApiService.getFirstUnplayedGame().then(function(response) {
          return response.data;
        });
      });
    };

    vm.init = function() {
      vm.getGame().then(function(game) {
        vm.game = game;
        vm.hasGameStarted = vm.isBeforeNow(vm.game.startDateTime);
      });
    };
  }]);