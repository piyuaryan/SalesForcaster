<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="anonymousTemplate">
    <tiles:putAttribute name="body">

        <div class="container">
            <div ng-show="${error}" class="alert alert-danger">${error}</div>
            <form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
                <div class="form-group">
                    <label for="username">Username</label>
                    <i class="fa fa-key"></i>
                    <input type="text" name="username" id="username" class="form-control" ng-model="username" required/>
                    <span ng-show="form.username.$dirty && form.username.$error.required" class="help-block">Username is required</span>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <i class="fa fa-lock"></i>
                    <input type="password" name="password" id="password" class="form-control" ng-model="password" required/>
                    <span ng-show="form.password.$dirty && form.password.$error.required" class="help-block">Password is required</span>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-actions">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" ng-disabled="form.$invalid || dataLoading" class="btn btn-primary">Login</button>
                    <img ng-if="dataLoading"
                         src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA=="/>
                </div>
            </form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>