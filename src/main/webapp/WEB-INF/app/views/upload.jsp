<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container" ng-controller="uploadCtrl">
            <h1>Upload</h1>

            <div class="alert" ng-class="{'alert-success':uploadStatus,  'alert-danger':!uploadStatus}" ng-show="uploadStatusMsg">
                <a href="#" class="close" data-ng-click="uploadStatusMsg = null" aria-label="close">&times;</a>
                {{uploadStatusMsg}}
            </div>
            <form name="uploadForm" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="tag">Tag</label>
                    <input type="text" name="tag" id="tag" ng-model="fileTag" class="form-control" placeholder="Tag" required>
                </div>
                <div class="form-group">
                    <label for="inputFile">File input</label>
                    <input type="file" name="inputFile" id="inputFile" valid-file ng-model="xlsNgMdl" file-object="xlsFileObj"
                           accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" required>

                    <p class="help-block">Select Xls file.</p>
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> Overwrite
                    </label>
                </div>

                    <%--
                    <code>uploadForm.tag.$valid = {{uploadForm.tag.$valid}}</code><br>
                    <code>uploadForm.inputFile.$valid = {{uploadForm.inputFile.$valid}}</code><br>
                    <code>uploadForm.$valid = {{uploadForm.$valid}}</code><br>
                    --%>
                <div class="form-actions">
                    <button ng-click="uploadFile()" ng-disabled="uploadForm.$invalid || dataLoading" class="btn btn-default">Upload</button>
                    <img ng-if="dataLoading" src="${pageContext.request.contextPath}/resources/asset/images/loading.gif"/>
                        <%--
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        --%>
                </div>
            </form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>