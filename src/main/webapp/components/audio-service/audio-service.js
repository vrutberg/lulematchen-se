angular.module('app').factory('AudioService', [function() {
  return {
    playAudio: function(audioFile) {
      new Audio(audioFile).play();
    }
  };
}]);