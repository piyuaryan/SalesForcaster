<!DOCTYPE html>
<html data-ng-app="myApp">

<head lang="en">
    <meta charset="utf-8">
    <title>Active Basket</title>
    <link rel="stylesheet" type="text/css" href="http://angular-ui.github.com/ng-grid/css/ng-grid.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.2/angular.min.js"></script>
    <script type="text/javascript" src="http://angular-ui.github.com/ng-grid/lib/ng-grid.debug.js"></script>
    <link rel="stylesheet" type="text/css" href="static/AngularJSService/css/style.css" />
    <script type="text/javascript">

        var app = angular.module('myApp', ['ngGrid']);

        app.controller('MyController', function($scope, $http) {
            $scope.filterOptions = {
                filterText: "",
                useExternalFilter: true
            };
            $scope.totalServerItems = 0;
            $scope.pagingOptions = {
                pageSizes: [20, 50, 100],
                pageSize: 20,
                currentPage: 1
            };
            $scope.setPagingData = function(data, page, pageSize){
                var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
                $scope.myData = pagedData;
                $scope.totalServerItems = data.length;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            };
            $scope.getPagedDataAsync = function (pageSize, page, searchText) {
                setTimeout(function () {
                    var data;
                    if (searchText) {
                        var ft = searchText.toLowerCase();
                        $http.get('getPaginationData/'+pageSize+"/"+page).success(function (largeLoad) {
                            data = largeLoad.filter(function(item) {
                                return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                            });
                            $scope.setPagingData(data,page,pageSize);
                        });
                    } else {
                        $http.get('getPaginationData/'+pageSize+"/"+page).success(function (largeLoad) {
                            $scope.setPagingData(largeLoad,page,pageSize);
                        });
                    }
                }, 100);
            };

            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

            $scope.$watch('pagingOptions', function (newVal, oldVal) {
                if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
                    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
                }
            }, true);
            $scope.$watch('filterOptions', function (newVal, oldVal) {
                if (newVal !== oldVal) {
                    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
                }
            }, true);

            $scope.gridOptions = {
                data: 'myData',
                enablePaging: true,
                showFooter: true,
                totalServerItems:'totalServerItems',
                pagingOptions: $scope.pagingOptions,
                filterOptions: $scope.filterOptions
            };
        });

    </script>
</head>

<body data-ng-controller="MyController" style="overflow: auto;">

<div class='tab'>
    <div class="activeTeamsDropdownStyle">
        <b>Spring MVC Server Side Pagination ng-grid AngularJS</b>
    </div>

    <div class="filler"></div>
    <div class="gridModelStyle" data-ng-grid="gridOptions"></div>
</div>

</body>

</html>