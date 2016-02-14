angular.module('app').factory('AudioService', ['SettingsService', 'Modernizr', function(SettingsService, Modernizr) {
  var shouldPlayAudio = function() {
    return SettingsService.isAudioEnabled() && Modernizr.autoplay;
  };

  return {
    playAudio: function(audioFile) {
      if (shouldPlayAudio()) {
        new Audio(audioFile).play();
      }
    }
  };
}]);