/**
 * Created by Radek on 09.11.2015.
 */
(function(){
    var app = angular.module('MainApp.admin', ['ngResource']);

    app.config(['$routeProvider', function(routeProvider){
        routeProvider.
            when('/admin',{
                templateUrl: 'myapp/admin/admin.html',
                controller:     'AdminCtrl',
                controllerAs:   'adminCtrl',
                data: {pageTitle: 'Admin'}
            });
    }]);

})();