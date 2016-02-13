angular.module('app').directive('hamburgerMenu', function(ApiService, $interval) {
  return {
    templateUrl: 'components/hamburger-menu-directive/hamburger-menu-template.html',

    controller: ['$scope', function($scope) {
      $('.drawer').drawer({
        iscroll: {
          // Configuring the iScroll
          // https://github.com/cubiq/iscroll#configuring-the-iscroll
          mouseWheel: true,
          preventDefault: false
        },
        showOverlay: true
      });

    }]
  };
});