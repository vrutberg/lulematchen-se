angular.module('app').directive('countdown', function() {
  return {
    template: '{{getCountdownString(from, to)}}',
    scope: {
      from: '=',
      to: '='
    },

    controller: ['$scope', function($scope) {
      $scope.getCountdownString = function (from, to) {
        var outputString = "NU";
        var difference = to - from;

        if (difference > 0) {
          outputString = "";

          var duration = moment.duration(difference);
          var tmpValue;

          if (duration.days() > 0) {
            if (outputString.length > 0) {
              outputString += ", ";
            }

            tmpValue = duration.days();
            outputString += tmpValue + (tmpValue == 1 ? " dag" : " dagar");
            duration.subtract(tmpValue, 'd');
          }

          if (outputString.length > 0 || duration.hours() > 0) {
            if (outputString.length > 0) {
              outputString += ", ";
            }

            tmpValue = duration.hours();
            outputString += tmpValue + (tmpValue == 1 ? " timme" : " timmar");
            duration.subtract(tmpValue, 'h');
          }

          if (outputString.length > 0 || duration.minutes() > 0) {
            if (outputString.length > 0) {
              outputString += ", ";
            }

            tmpValue = duration.minutes();
            outputString += tmpValue + (tmpValue == 1 ? " minut" : " minuter");
            duration.subtract(tmpValue, 'm');
          }

          if (outputString.length > 0) {
            outputString += ", ";
          }

          tmpValue = duration.seconds();
          outputString += tmpValue + (tmpValue == 1 ? " sekund" : " sekunder");
          duration.subtract(tmpValue, 's');
        }

        return outputString;
      };
    }]
  };
});