<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>

    <c:url value="/resources/static/css/edit-user.css" var="editusercss"/>
    <link href="${editusercss}" rel="stylesheet" type="text/css">
</head>
<body>
<t:wrapper>
    <!-- Sidebar -->
    <ul class="sidebar navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="userlist">
                <span>Users</span>
            </a>
        </li>

        <li class="nav-item">
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
                    <a href="userlist">User List</a>
                </li>
            </ol>

            <form id="form_editUser">
                <div class="form-row">
                    <div class="form-group col-md-10">
                    </div>
                    <div class="form-group col-md-2">
                        <input id="chkToggle1" type="checkbox" checked data-toggle="toggle" data-on="Edit" data-off="Cancel" data-onstyle="success" data-offstyle="danger">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_storeName_edit">Store Name</label>
                        <input type="text" class="form-control" id="inp_storeName_edit" placeholder="Store Name"
                               required
                               maxlength="128"
                               disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_userName_edit">User Name</label>
                        <input type="text" class="form-control" id="inp_userName_edit" placeholder="User Name" required
                               maxlength="128"
                               disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_userID_edit">User ID</label>
                        <input type="text" class="form-control" id="inp_userID_edit" placeholder="User ID" required
                               maxlength="32"
                               disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_country_edit">Country</label>
                        <select id="inp_country_edit" class="form-control" disabled>
                            <option value="" disabled selected>Select your option</option>
                            <option value="KN">NORTH KOREA (KN)</option>
                            <option value="KR">KOREA (KR)</option>
                            <option value="KV">KOSOVO (KV)</option>
                            <option value="KW">KUWAIT (KW)</option>
                            <option value="LA">LAOS (LA)</option>
                            <option value="LB">LEBANON (LB)</option>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_password_edit">Password</label>
                        <input type="password" class="form-control" id="inp_password_edit" placeholder="Password"
                               required
                               maxlength="32"
                               disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_compassword_edit">Confirm Password</label>
                        <input type="password" class="form-control" id="inp_compassword_edit"
                               placeholder="Confirm Password"
                               required maxlength="32"
                               disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_city_edit">City</label>
                        <input type="text" class="form-control" id="inp_city_edit" placeholder="City" maxlength="128" disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_address_edit">Address</label>
                        <textarea class="form-control" rows="1" id="inp_address_edit" placeholder="Address"
                                  maxlength="256" disabled></textarea>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_email_edit">Email</label>
                        <input type="email" class="form-control" id="inp_email_edit" placeholder="Email" maxlength="64" disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_tel_No_edit">Tel No</label>
                        <input type="text" class="form-control" id="inp_tel_No_edit" placeholder="Tel No"
                               maxlength="20" disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_hp_No_edit">HP No</label>
                        <input type="text" class="form-control" id="inp_hp_No_edit" placeholder="HP No" maxlength="20" disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_permission_edit">Permission</label>
                        <select id="inp_permission_edit" class="form-control" disabled>
                            <option value="0">User</option>
                            <option value="1">Admin</option>
                            <option value="2">Manager</option>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_ManagerID_edit">Manager ID</label>
                        <input type="text" class="form-control" id="inp_ManagerID_edit" placeholder="Manager ID"
                               maxlength="32"
                               disabled>
                    </div>
                </div>
                <div class="btn-toolbar" role="toolbar">
                    <div class="btn-group mr-2" role="group">
                        <button id="btn_save_user_edit" class="btn btn-primary" type="submit" disabled><i class="fas fa-save"></i> Save</button>
                    </div>
                    <div class="btn-group mr-2" role="group">
                        <button class="btn btn-primary" type="button" onclick="location.href = 'userlist'"><i class="fas fa-ban"></i> Cancel</button>
                    </div>
                </div>
            </form>

            <br>
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
                                <th>Store Name</th>
                                <th>User Name</th>
                                <th>App Version</th>
                                <th>Registration date</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="device" items="${devices}">
                            <tr>
                                <td></td>
                                <td hidden>${device.sequence}</td>
                                <td>${device.optical_number}</td>
                                <td>${device.user_sequence.username}</td>
                                <td>${device.user_sequence.store_name}</td>
                                <td>${device.app_version}</td>
                                <td>${device.reg_date}</td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        </div>
    </div>
</t:wrapper>

<c:url value="/resources/static/js/devices.js" var="devicesjs"/>
<script src="${devicesjs}" type="text/javascript"></script>

<c:url value="/resources/static/js/edituser.js" var="edituser"/>
<script src="${edituser}" type="text/javascript"></script>
</body>
</html>
