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
        <li class="nav-item">
            <a class="nav-link" href="/">
                <span>Users</span>
            </a>
        </li>

        <li class="nav-item active">
            <a class="nav-link" href="devices">
                <span>Devices</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="settings">
                <span>Settings</span>
            </a>
        </li>
    </ul>

    <div id="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="devices">Devices List</a>
                </li>
            </ol>

            <form id="form_addDevice" method="post">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inp_opticNumber_device">Optic Number</label>
                        <input type="text" class="form-control" id="inp_opticNumber_device" placeholder="Optic Number"
                               required
                               maxlength="128" name="">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_registerDate_device">Register Date</label>
                        <input type="text" class="form-control" id="inp_registerDate_device"
                               placeholder="Register Date"
                               required
                               maxlength="128" name="">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_appVersion_device">App Version</label>
                        <input type="text" class="form-control" id="inp_appVersion_device" placeholder="App Version"
                               required
                               maxlength="128" name="">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_userName_device">User Name</label>
                        <select id="inp_userName_device" class="form-control" name="">
                            <option value="" disabled selected>Select username</option>
                            <c:forEach var="user" items="${users}">
                                <option value="${user.sequence}">${user.username}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="btn-toolbar" role="toolbar">
                    <div class="btn-group mr-2" role="group">
                        <button id="btn_save_device" class="btn btn-primary" type="submit"><i class="fas fa-save"></i>
                            Save
                        </button>
                    </div>
                    <div class="btn-group mr-2" role="group">
                        <button class="btn btn-primary" type="button" onclick="location.href = 'devices'"><i
                                class="fas fa-ban"></i> Cancel
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</t:wrapper>

<c:url value="/resources/static/js/adddevices.js" var="adddevices"/>
<script src="${adddevices}" type="text/javascript"></script>
</body>
</html>
