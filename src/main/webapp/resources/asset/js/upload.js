/**
 * This directive is used to validate File element. This is used for Form Validations.
 * require: 'ngModel' This means element have to have attribute "ng-model" for this directive to work.
 *
 * eg. <input type="file" valid-file ng-model="xlsNgMdl" />
 * Sets: {{uploadForm.inputFile.$valid}} and {{uploadForm.$valid}}
 */
app.directive('validFile', function () {
    return {
        require: 'ngModel',
        link: function (scope, el, attrs, ngModel) {
            el.bind('change', function () {
                scope.$apply(function () {
                    ngModel.$setViewValue(el.val());
                    ngModel.$render();
                });
            });
        }
    }
});

/**
 * This directive is used for creating Multipart file object (Model).
 * Restrict = 'A' means it will apply to only elements with "file-object" attribute.
 *
 * eg: <input type="file" file-object="mdlName">
 * So, in JS file user can access file model like: $scope.mdlName
 */
app.directive('fileObject', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileObject);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);


/**
 * This controller is used for All File Upload operations
 */
app.controller('uploadCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.uploadStatus = true;
    $scope.uploadFile = function () {

        if (!$scope.uploadForm.$valid) {
            return;
        }
        $scope.dataLoading = true;

        var config = {
            // this cancels AngularJS normal serialization of request
            transformRequest: angular.identity,
            // this lets browser set `Content-Type: multipart/form-data`
            // header and proper data boundary
            headers: {'Content-Type': undefined}
        };

        var fd = new FormData();
        fd.append('fileTag', $scope.fileTag);
        fd.append('file', $scope.xlsFileObj);

        $http.post('/upload/xls2', fd, config)
            //.success(function (data, status, headers, config) {
            .success(function (response, status) {
                if (response.status == "success") {
                    $scope.uploadStatus = true; //"Success";
                    $scope.uploadStatusMsg = "Uploaded Successfully.";
                }
                else {
                    $scope.uploadStatus = false;
                    $scope.uploadStatusMsg = "Upload failed. Error: " + status + "-" + response.errorMsg;
                }
                $scope.dataLoading = false;
            })
            .error(function (response, status) {
                $scope.uploadStatus = false;
                $scope.uploadStatusMsg = status + " - Couldn't Upload. Error: " + response.errorMsg;
                $scope.dataLoading = false;
            });
    };

}]);
