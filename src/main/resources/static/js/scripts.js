var app = angular.module('myApp', ['smart-table','ngRoute','pikaday','angularMoment']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
    when('/wyszukiwanieFaktur', {
        templateUrl: 'templates/wyszukiwanieFaktur.html',
        controller: 'wyszukiwanieFaktur',
        controllerAs: 'ctrl'
    }).    
    when('/test', {
        templateUrl: 'templates/test.html',
        controller: 'test',
        controllerAs: 'ctrl'
    }).
    when('/hello', {
        templateUrl: 'templates/hello.html'
    }).
    otherwise({
        redirect: '/'
    });
}]);

app.config(['pikadayConfigProvider', function(pikaday) {

  var locales = {
    pl: {
        previousMonth : 'Poprzedni miesiąc',
        nextMonth     : 'Następny miesiąc',
        months        : ['Styczeń','Luty','Marzec','Kwiecień','Maj','Czerwiec','Lipiec','Sierpień','Wrzesień','Październik','Listopad','Grudzień'],
        weekdays      : ['Niedziela','Poniedziałek','Wtorek','Środa','Czwartek','Piątek','Sobota'],
        weekdaysShort : ['Nie','Pon','Wto','Śro','Czw','Pią','Sob']
    }
  };

  pikaday.setConfig({
    i18n: locales.pl, // sets the default language [optional]
    locales: locales, // required if setting the language using the i18n attribute
    minDate: new Date('2011-01-01'),
    maxDate: new Date(),
    defaultDate : new Date(),        
    format: 'YYYY/MM/DD'
  });
}]);


app.filter('pln', function () {
    return function (text) {return text+' zł';};
});
