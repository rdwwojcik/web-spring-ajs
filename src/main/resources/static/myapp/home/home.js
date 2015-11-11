/**
 * Created by Radek on 07.10.2015.
 */
(function() {
    var app = angular.module('MainApp.home', ['ngResource']);

    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/home', {
                templateUrl: 'myapp/home/home.html',
                controller:     'HomeCtrl',
                controllerAs:   'homeCtrl',
                data: {pageTitle: 'Home'}
            });
    }]);

    app.factory('sessionService', function(){
       var session = {};
        session.foo = function(){
            alert('Przylkadowa wiadomosc: Services')
        };

        session.login = function(data){
            alert('User Logged ' + data.name + " and " + data.password);
          localStorage.setItem("session", data);
        };

        session.logout = function(){
          localStorage.removeItem("session");
        };

        session.isLoggedIn = function(){
          localStorage.getItem("session") !== null;
        };

        return session;
    });

    app.factory('accountService', function($resource){
        var service = {};
        service.register = function(account, success, failure){
            var Account = $resource('http://localhost:8080/rest/accounts');
            Account.save({}, account, success, failure);
        };

        service.userExist = function(account, success, failure){
            var Account = $resource('http://localhost:8080/rest/accounts');
            var data = Account.get({name:account.name},
                function(){
                    var accounts = data.accounts;
                    if(accounts.length !== 0) {
                        success(accounts[0]);
                    }
                    else{
                        failure();
                    }
                },
                failure);
        };

        return service;
    });

    app.factory('messageService', function(){

        return {
            comments: []
        };

    });

    app.controller('HomeCtrl', function HomeController($scope, sessionService, accountService) {

        this.wiadomosc = 'info2';

        this.login = function(){
            accountService.userExist($scope.account, function(account){
                    sessionService.login(account);
                    console.log(account.name);
            },
            function(){
                alert("Error logging in user");
            });

            var dane = localStorage.getItem("session");
            this.wiadomosc = dane.name;
        };

        //this.login = function(){
        //    accountService.register($scope.account,
        //        function (returnedData) {
        //            sessionService.login(returnedData);
        //        },
        //        function(){
        //            alert("Error registering user");
        //        }
        //    );
        //    //sessionService.login($scope.account);
        //};
    });

})();