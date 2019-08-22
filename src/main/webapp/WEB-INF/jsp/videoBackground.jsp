<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
</head>
<body>

<t:wrapper>
    <!-- Sidebar -->
    <ul class="sidebar navbar-nav">
        <!-- <li class="nav-item">
            <a class="nav-link" href="userlist">
                <span>Users</span>
            </a>
        </li> -->

        <li class="nav-item active">
            <a class="nav-link" href="devices">
                <span>Devices</span>
            </a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="contents">
                <span>Video & Background Image</span>
            </a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="products">
                <span>Recommended Product</span>
            </a>
        </li>

        <!-- <li class="nav-item">
            <a class="nav-link" href="settings">
                <span>Settings</span>
            </a>
        </li> -->
    </ul>

    <div id="content-wrapper">
        <div class="container-fluid">

            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="devices">Devices List</a>
                </li>

                <!-- <li class="breadcrumb-item">
                    <a href="viewdevices">Devices Detail</a>
                </li> -->
            </ol>

            <!-- DataTables Example -->
            <div class="card mb-3">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="deviceTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th></th>
                                <th hidden></th>
                                <th>Optical Number</th>
                                <th>App Version</th>
                                <th>Registration date</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="device" items="${devices}">
                            <tr>
                                <td></td>
                                <td hidden>${device.id}</td>
                                <td>${device.opticalNumber}</td>
                                <td>${device.appVersion}</td>
                                <td>${device.datecreated}</td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <%-- Button --%>
        <div class="btn-toolbar custom-group" role="toolbar">
            <div class="btn-group mr-2" role="group">
                <a class="btn btn-primary" type="submit" href=contents><i class="fas fa-plus-circle"></i> Set the contents of the selected devices</a>
            </div>
        </div>
    </div>
</t:wrapper>

<!-- Device Modal-->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete Device</h5>
            </div>
            <div class="modal-body">Are you sure you want to delete the selected Device?</div>
            <div class="modal-footer">
                <button id="btn_delete_device_yes" class="btn btn-primary" type="button" data-dismiss="modal">Yes</button>
                <button class="btn btn-secondary" type="button" data-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>


<c:url value="/resources/static/js/devices.js" var="devicesjs"/>
<script src="${devicesjs}" type="text/javascript"></script>

</body>
</html>
