angular.module('app').directive('teamLogo', function() {
  return {
    template: '<img class="team-logo" ng-src="images/team-logos/{{teamCode | lowercase}}.svg">',
    restrict: 'E',
    scope: {
      teamCode: '='
    }
  };
});