//angular.module('menu', []).controller('menuCtrl',  function ($scope) {
app.controller('menuCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {

    $scope.login = function () {
        $scope.dataLoading = true;

        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8'
            }
        };

        $http.post('/j_spring_security_check', $("#loginForm").serialize(), config)
            .success(function (data, status, headers, config) {
                $scope.error = undefined;
                if (status == 200) {
                    if (data.indexOf("Login Page") < 0) {
                        $window.location.href = '/welcome';
                    }
                    else {
                        $scope.error = "Couldn't authenticate";
                    }
                    $scope.dataLoading = false;
                }
            })
            .error(function (response, status) {
                $scope.error = "Couldn't authenticate";
                $scope.dataLoading = false;
            });

    };

    $scope.logout = function () {
        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8'
            }
        };

        $http.post('/j_spring_security_logout', $("#logoutForm").serialize(), config)
            .success(function (response, status) {
                if (status == 200) {
                    $window.location.href = '/';
                }
            })
            .error(function (response, status) {
                alert("Couldn't Log Out !!!");
                $window.location.href = '/';
            });
    }

}]);
