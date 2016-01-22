describe('CountdownDirective', function() {
  beforeEach(module('app'));

  var $compile;
  var $rootScope;

  beforeEach(inject(function (_$compile_, _$rootScope_) {
    $compile = _$compile_;
    $rootScope = _$rootScope_;
  }));

  describe('$scope.getCountdownString', function() {
    it('should correctly format the countdown to one second', function() {
      $rootScope.from = 1000;
      $rootScope.to = 2000;

      var element = $compile('<p countdown from="from" to="to"></p>')($rootScope);

      $rootScope.$digest();

      expect(element.text()).toBe('1 sekund');
    });

    it('should correctly format the countdown to two seconds', function() {
      $rootScope.from = 1000;
      $rootScope.to = 3000;

      var element = $compile('<p countdown from="from" to="to"></p>')($rootScope);

      $rootScope.$digest();

      expect(element.text()).toBe('2 sekunder');
    });

    it('should correctly format the countdown to 30 minutes', function() {
      $rootScope.from = 1000;
      $rootScope.to = 1801000;

      var element = $compile('<p countdown from="from" to="to"></p>')($rootScope);

      $rootScope.$digest();

      expect(element.text()).toBe('30 minuter, 0 sekunder');
    });

    it('should correctly format the countdown to 1 hour', function() {
      $rootScope.from = 1000;
      $rootScope.to = 3601000;

      var element = $compile('<p countdown from="from" to="to"></p>')($rootScope);

      $rootScope.$digest();

      expect(element.text()).toBe('1 timme, 0 minuter, 0 sekunder');
    });

    it('should correctly format the countdown to 1 hour and 10 seconds', function() {
      $rootScope.from = 1000;
      $rootScope.to = 3611000;

      var element = $compile('<p countdown from="from" to="to"></p>')($rootScope);

      $rootScope.$digest();

      expect(element.text()).toBe('1 timme, 0 minuter, 10 sekunder');
    });

    it('should correctly format the countdown to 1 day', function() {
      $rootScope.from = 1000;
      $rootScope.to = 86401000;

      var element = $compile('<p countdown from="from" to="to"></p>')($rootScope);

      $rootScope.$digest();

      expect(element.text()).toBe('1 dag, 0 timmar, 0 minuter, 0 sekunder');
    });

    it('should correctly format the countdown to 1 day, 1 hour, 1 minute, and 1 second', function() {
      $rootScope.from = 1000;
      $rootScope.to = 90062000;

      var element = $compile('<p countdown from="from" to="to"></p>')($rootScope);

      $rootScope.$digest();

      expect(element.text()).toBe('1 dag, 1 timme, 1 minut, 1 sekund');
    });
  });
});