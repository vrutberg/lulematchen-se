describe('ApiService', function() {
  beforeEach(module('app'));

  var ApiService;
  var $httpBackend;

  beforeEach(inject(function ($injector, _ApiService_) {
    ApiService = _ApiService_;
    $httpBackend = $injector.get('$httpBackend');
  }));

  afterEach(function() {
    // $httpBackend.verifyNoOutstandingExpectation();
    $httpBackend.verifyNoOutstandingRequest();
  });

  describe('getGames', function() {
    it('should perform a GET request on the correct URL', function(done) {
      var responseData = { a: '123' };

      ApiService.getGames().then(function(response) {
        expect(response.data).toEqual(responseData);
        done();
      });

      $httpBackend.expectGET('_api/games').respond(200, responseData);
      $httpBackend.flush();
    });
  });

  describe('getLastPlayedGame', function() {
    it('should perform a GET request on the correct URL', function(done) {
      var responseData = { a: '123' };

      ApiService.getLastPlayedGame().then(function(response) {
        expect(response.data).toEqual(responseData);
        done();
      });

      $httpBackend.expectGET('_api/games/lastPlayed').respond(200, responseData);
      $httpBackend.flush();
    });
  });

  describe('getFirstUnplayedGame', function() {
    it('should perform a GET request on the correct URL', function(done) {
      var responseData = { a: '123' };

      ApiService.getFirstUnplayedGame().then(function(response) {
        expect(response.data).toEqual(responseData);
        done();
      });

      $httpBackend.expectGET('_api/games/firstUnplayed').respond(200, responseData);
      $httpBackend.flush();
    });
  });

  describe('getGameDetails', function() {
    it('should perform a GET request on the correct URL', function(done) {
      var responseData = { a: '123' };
      var gameId = 123;

      ApiService.getGameDetails(gameId).then(function(response) {
        expect(response.data).toEqual(responseData);
        done();
      });

      $httpBackend.expectGET('_api/games/' + gameId + '/details').respond(200, responseData);
      $httpBackend.flush();
    });
  });

  describe('getTodaysGames', function() {
    it('should perform a GET request on the correct URL', function(done) {
      var responseData = { a: '123' };

      ApiService.getTodaysGames().then(function(response) {
        expect(response.data).toEqual(responseData);
        done();
      });

      $httpBackend.expectGET('_api/games/todaysGames').respond(200, responseData);
      $httpBackend.flush();
    });
  });
});