angular.module('app').directive('teamLogo', function() {
  return {
    template: '<img class="team-logo" ng-src="images/team-logos/{{getImageName()}}.svg">',
    restrict: 'E',
    scope: {
      teamCode: '='
    },
    controller: ['$scope', 'SettingsService', function($scope, SettingsService) {
      $scope.getImageName = function() {
        if ($scope.teamCode === 'LHF' && SettingsService.isRetroModeEnabled()) {
          return 'lhf-retro';
        } else {
          return $scope.teamCode.toLowerCase();
        }
      };
    }]
  };
});