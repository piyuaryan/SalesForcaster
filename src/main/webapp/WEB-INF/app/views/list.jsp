<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container" ng-controller="listCtrl">
            <h1>List</h1>

            <div class="alert" ng-class="{'alert-success':listStatus,  'alert-danger':!listStatus}" ng-show="listStatusMsg">
                <a href="#" class="close" data-ng-click="listStatusMsg = null" aria-label="close">&times;</a>
                {{listStatusMsg}}
            </div>
            <form name="listForm">
                <div class="form-actions">
                    <button ng-click="listFile()" ng-disabled="dataLoading" class="btn btn-default">List</button>
                    <img ng-if="dataLoading" src="${pageContext.request.contextPath}/resources/asset/images/loading.gif"/>
                </div>
            </form>

            <div class='tab'>
                <div class="activeTeamsDropdownStyle">
                    <b>Spring MVC Server Side Pagination ng-grid AngularJS</b>
                </div>

                <div class="filler"></div>
                <div class="gridModelStyle" data-ng-grid="gridOptions"></div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>