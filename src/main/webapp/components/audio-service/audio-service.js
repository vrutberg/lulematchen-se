angular.module('app').factory('AudioService', ['SettingsService', 'Modernizr', function(SettingsService, Modernizr) {
  return {
    playAudio: function(audioFile) {
      new Audio(audioFile).play();
    }
  };
}]);