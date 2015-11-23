/**
 * This controller is used for All Listing operations
 */
app.controller('listCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.listStatus = true;
    $scope.listFile = function () {

        $scope.dataLoading = true;

        $http.post('/data/getWeeklyProfits')
            //.success(function (data, status, headers, config) {
            .success(function (response, status) {
                if (response.status == "success") {
                    $scope.listStatus = true; //"Success";
                    $scope.listStatusMsg = "listed Successfully.";
                }
                else {
                    $scope.listStatus = false;
                    $scope.listStatusMsg = "list failed. Error: " + status + "-" + response.errorMsg;
                }
                $scope.dataLoading = false;
            })
            .error(function (response, status) {
                $scope.listStatus = false;
                $scope.listStatusMsg = status + " - Couldn't list. Error: " + response.errorMsg;
                $scope.dataLoading = false;
            });
    };

    $scope.getWeatherInfo = function () {

        $scope.dataLoading = true;

        $http.post('/data/getWeatherInfo')
            //.success(function (data, status, headers, config) {
            .success(function (response, status) {
                if (response.status == "success") {
                    $scope.listStatus = true; //"Success";
                    $scope.listStatusMsg = "Done";
                }
                else {
                    $scope.listStatus = false;
                    $scope.listStatusMsg = "Error: " + status + "-" + response.errorMsg;
                }
                $scope.dataLoading = false;
            })
            .error(function (response, status) {
                $scope.listStatus = false;
                $scope.listStatusMsg = status + " - Couldn't fetch Weather info. Error: " + response.errorMsg;
                $scope.dataLoading = false;
            });
    };

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
}]);
