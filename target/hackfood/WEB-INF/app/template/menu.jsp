<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Start Logo & Naviagtion -->
<div class="navbar navbar-default navbar-top" ng-controller="menuCtrl">

    <div class="navbar-collapse collapse">
        <j:choose>
            <j:when test="${pageContext.request.userPrincipal.authenticated}">

                <!-- Stat Search -->
                <%--  <div class="search-side">
                      <a href="#" class="show-search"><i class="fa fa-search"></i></a>

                      <div class="search-form">
                          <form autocomplete="off" role="search" method="get" class="searchform" action="#">
                              <input type="text" value="" name="s" id="s" placeholder="Search hackfood.com">
                          </form>
                      </div>
                  </div>
                --%>  <!-- End Search -->

                <!-- Start Navigation List -->
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="active" href="<c:url value="/welcome" />">Home</a>
                            <%--     <ul class="dropdown">
                                     <li><a class="active" href="<c:url value="/about" />">about</a>
                                     </li>
                                 </ul>--%>
                    </li>
                    <li>
                        <a href="<c:url value="/upload" />">Upload</a>
                    </li>
                    <li>
                        <a href="<c:url value="/data" />">Data</a>
                    </li>
                    <li>
                        <a href="<c:url value="/data/list" />">List</a>
                    </li>
                    <li>
                        <a href="<c:url value="/about" />">About</a>
                    </li>
                    <li>
                        <a href="#" ng-click="logout()">Logout</a>
                    </li>
                </ul>
                <!-- End Navigation List -->
            </j:when>
            <j:otherwise>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" data-toggle="modal" data-target="#myModal">Login</a>
                    </li>
                </ul>
            </j:otherwise>
        </j:choose>
    </div>


    <div id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Login</h4>
                </div>
                <div class="modal-body">
                    <div ng-show="error" class="alert alert-danger">{{error}}</div>
                    <form id="loginForm" name='loginForm' ng-submit="login()" role="form">
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
                        <%--
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        --%>
                        <div class="form-actions">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="submit" ng-disabled="form.$invalid || dataLoading" class="btn btn-primary">Login</button>
                            <img ng-if="dataLoading"
                                 src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA=="/>
                        </div>
                    </form>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
</div>
<!-- End Header Logo & Naviagtion -->
