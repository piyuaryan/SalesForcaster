var app = angular.module('salesForecaster', ['ngGrid']);

app.controller('appCtrl', ['$scope', function ($scope) {
    $scope.master = {};

    $scope.showLoginDiv = function () {
        alert("show login div");
    };

    $scope.login = function () {
        alert("login clicked");
    };
}]);

// utilities
app.utility = {};

app.utility.isObjectNull = function (obj) {
    return typeof(obj) == 'undefined' || obj == undefined || !obj;
};

/*
 (function () {
 var app = angular.module('salesForecaster', []);

 app.controller('appCtrl', function () {
 showLoginDiv = function () {
 alert("show login div");
 };

 login = function () {
 alert("login clicked");
 };

 })
 })();

 */