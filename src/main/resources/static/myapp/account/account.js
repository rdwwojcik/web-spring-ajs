/**
 * Created by Radek on 07.10.2015.
 */
(function() {
    var app = angular.module('MainApp.account', ['ngResource']);

    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/account', {
                templateUrl: 'myapp/account/account.html',
                controller:     'AccountCtrl',
                controllerAs:   'accountCtrl',
                data: {pageTitle: 'Account'}
            }).
            when('/newaccount', {
                templateUrl: 'myapp/account/new_account.html',
                controller:     'AccountCtrl',
                controllerAs:   'accountCtrl',
                data: {pageTitle: 'New Account'}
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

        service.getAccounts = function(success){
            console.log('Call getAccounts');
            var accounts = [];
            var Account = $resource('http://localhost:8080/rest/accounts');
            var data = Account.get(
                function(){
                    accounts = data.accounts;
                    success(accounts);
                }
            );
        };

        return service;
    });

    app.controller('AccountCtrl', function HomeController($scope, sessionService, accountService) {

        this.wiadomosc = 'info2';
        this.login = function(){
            accountService.userExist($scope.account,
                function(account){
                    sessionService.login(account);
                },
                function(){
                    alert("Error logging in user");
                });
        };

        $scope.informacja = 'informacja';
        this.accountsList = function(){
            console.log('Call accounts function');
            accountService.getAccounts(
                function(accounts_data){
                    console.log('Call success');
                    $scope.informacja = 'informacja druga';
                    $scope.accounts_table = accounts_data;
                    console.log($scope.accounts_table);
                }
            );
        };
    });


    //app.service('acService', function($http, $q){
    //    var deferred = $q.defer();
    //    $http.get('http://localhost:8080/rest/accounts').then(function(data){
    //        deferred.resolve(data);
    //    });
    //
    //    this.getAccount = function(){
    //        console.log('Call getAccount');
    //        return deferred.promise;
    //    };
    //});

    //this.accountsList = function(){
        //var promise = acService.getAccount();
        //promise.then(function(data){
        //    console.log('Call promise');
        //    $scope.accounts_table = data.data.accounts;
        //    console.log($scope.accounts_table);
        //    $scope.informacja = 'informacja trzecia';
        //});
        //
        ////console.log('Call info');
        //$scope.informacja = 'informacja druga';
    //};
})();