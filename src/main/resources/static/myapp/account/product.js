/**
 * Created by Radek on 07.10.2015.
 */
(function(){
    var app = angular.module('MainApp.product', []);

    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/product', {
                templateUrl: 'myapp/account/product.html',
                controller: 'ProductCtrl',
                controllerAs: 'productCtrl',
                data: {pageTitle: 'Product'}
            });
    }]);

    app.controller('ProductCtrl', function ProductController($scope) {
        this.products = gems;
    });

    var gems = [
        {
            name: 'Kwota zakupu',
            price: 15,
            desc: '.....',
            canPurchase: true,
            soldOut: false
        },
        {
            name: 'Kwota zakupu',
            price: 10,
            desc: 'Jakiś tam produkt',
            canPurchase: true,
            soldOut: false
        }
    ];
})();