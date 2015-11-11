/**
 * Created by Radek on 27.09.2015.
 */
(function(){
    var app = angular.module('MainApp', [
        'ngResource'
        ,'ngRoute'
        ,'angularMoment'
        ,'MainApp.home'
        ,'MainApp.product'
        ,'MainApp.admin'
    ]);

    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.otherwise({
            templateUrl: 'myapp/home/home.html'
            });
    }]);

    app.run(function run(){
    });

    app.controller( 'AppCtrl', function AppCtrl ($scope, $location) {

        this.leftMenu = 'menu.html';
        this.pageTitle = 'Przyklad';

        this.ustawMenu = function(nameMenu, title){
            this.leftMenu = nameMenu;
            this.pageTitle = title;
        };

        //console.log('test');
        //$scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
        //    console.log('jest ok');
        //    if ( angular.isDefined( toState.data.pageTitle ) ) {
        //        $scope.pageTitle = toState.data.pageTitle + ' | ngBoilerplate' ;
        //    }
        //});
    });

    app.factory("Post", function($resource) {
        return $resource("http://localhost:8080/web-spring-ajs/rest/accounts/200", {}, {
            query: { method: "GET", isArray: false }
        });
    });

    app.controller("PostIndexCtrl", function($scope, Post) {
        Post.query(function(data) {
            console.log(data)
            $scope.posts = data;
            console.log($scope.posts);
        });
        //Post.query(function(data) {
        //    console.log(data);
        //    this.posts = data;
        //    console.log(this.posts);
        //});
    });

    //app.factory("Post", function($resource) {
    //    return $resource('http://localhost:8080/rest/accounts/:id'); // Note the full endpoint address
    //});
    //
    //app.controller("PostIndexCtrl", function($scope, Post) {
    //    Post.query(function(data) {
    //        //console.log(data);
    //        $scope.posts = data;
    //    });
    //});
    //
    //app.controller("PostShowCtrl", function($scope, Post) {
    //    Post.get({ id: 200 }, function(data) {
    //        $scope.post = data;
    //    });
    //});

    //app.controller('myCtrl', function($http){
    //    $http.get('http://localhost:8080/rest/accounts/200').
    //        success(function(data) {
    //            this.greeting = data;
    //        });
    //});

    //app.config(['$routeProvider', function ($routeProvider) {
    //    $routeProvider.
    //        when('/product', {
    //            templateUrl: 'templates/product.html',
    //            controller: 'StoreController',
    //            controllerAs: 'store'
    //        }).
    //        otherwise({
    //            redirect: '/'
    //        });
    //}]);
})();