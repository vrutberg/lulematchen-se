angular.module('app').directive('hamburgerMenu', function() {
  return {
    templateUrl: 'components/hamburger-menu-directive/hamburger-menu-template.html',

    scope: {},

    controller: ['$scope', function($scope) {
      $scope.isAutoplaySupported = false;

      $scope.$watch(function() { return Modernizr.autoplay; }, function(newValue, oldValue) {
        $scope.isAutoplaySupported = newValue;
      });
    }],

    link: function() {
      $('body').addClass('drawer drawer--right');

      $('.drawer').drawer({
        iscroll: {
          // Configuring the iScroll
          // https://github.com/cubiq/iscroll#configuring-the-iscroll
          mouseWheel: true,
          preventDefault: false
        },
        showOverlay: true
      });
    }
  };
});