angular.module('app').directive('teamLogo', function() {
  return {
    template: '<img class="team-logo" ng-src="images/team-logos/{{teamCode | lowercase}}/logo.jpg">',
    restrict: 'E',
    scope: {
      teamCode: '='
    }
  };
});