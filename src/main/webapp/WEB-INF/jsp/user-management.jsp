<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>

    <c:url value="/resources/static/css/user.css" var="usercss"/>
    <link href="${usercss}" rel="stylesheet" type="text/css">
</head>
<body>
<t:wrapper>
    <!-- Sidebar -->
    <ul class="sidebar navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="user-management">
                <span>Users</span>
            </a>
        </li>
       

        <li class="nav-item">
            <a class="nav-link" href="devices">
                <span>Devices</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="devices">
                <span>Video & Background Image</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="devices">
                <span>Recommended Product</span>
            </a>
        </li>
        
		<!--  
        <li class="nav-item">
            <a class="nav-link" href="settings">
                <span>Settings</span>
            </a>
        </li>
        -->
    </ul>

    <div id="content-wrapper">
        <div class="container-fluid">

            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="user-management">User List</a>
                </li>

                <li class="breadcrumb-item active">
                    <a href="viewuser" id="link_viewuser">User Detail</a>
                </li>
            </ol>

            <!-- DataTables Example -->
            <div class="card mb-3">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="userTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th></th>
                                <th>User Name</th>
                                <th>User ID</th>
                                <th>Store Name</th>
                                <th>Country</th>
                                <th>City</th>
                            </tr>
                            </thead>
                            <tbody>
	                            <c:forEach var="user" items="${users}">
	                            <tr ondblclick="location.href='user-details/${user.sequence}'">
	                                <td></td>
	                                <td>${user.userName}</td>
	                                <td>${user.userId}</td>
	                                <td>${user.storeName}</td>
	                                <td>${user.countryCode}</td>
	                                <td>${user.city}</td>
	                            </tr>
	                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
            <%-- Button --%>
        <div class="btn-toolbar custom-group" role="toolbar">
            <div class="btn-group mr-2" role="group">
                <button class="btn btn-primary" type="button" onclick="location.href = 'new-user'"><i class="fas fa-plus-circle"></i> New User</button>
            </div>
            <div class="btn-group mr-2" role="group">
                <button id="btn_delete_user" class="btn btn-primary" type="button" data-toggle="modal" data-target="#userModal"><i class="fas fa-minus-circle"></i> Delete</button>
            </div>
        </div>
    </div>
</t:wrapper>

<!-- User Modal-->
<div class="modal fade" id="userModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete User</h5>
            </div>
            <div class="modal-body">Are you sure you want to delete the selected User?</div>
            <div class="modal-footer">
                <button id="btn_delete_user_yes" class="btn btn-primary" type="button" data-dismiss="modal">Yes</button>
                <button class="btn btn-secondary" type="button" data-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>

<c:url value="/resources/static/js/user.js" var="userjs"/>
<script src="${userjs}" type="text/javascript"></script>
</body>
</html>
