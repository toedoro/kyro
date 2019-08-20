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
            <ol id="crumbs" class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="userlist">User List</a>
                </li>
            </ol>

            <form id="form_addUser">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inp_storeName">Store Name</label>
                        <input type="text" class="form-control" id="inp_storeName" placeholder="Store Name" required
                               maxlength="128" autocomplete="false">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_userName">Name</label>
                        <input type="text" class="form-control" id="inp_userName" placeholder="Name" required
                               maxlength="128" autocomplete="false">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_userID">User ID</label>
                        <input type="text" class="form-control" id="inp_userID" placeholder="User ID" required
                               maxlength="32" autocomplete="false">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_country">Country</label>
                        <select id="inp_country" class="form-control">
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
                        <label for="inp_password">Password</label>
                        <input type="password" class="form-control" id="inp_password" placeholder="Password" required
                               maxlength="32" autocomplete="false">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_compassword">Confirm Password</label>
                        <input type="password" class="form-control" id="inp_compassword" placeholder="Confirm Password"
                               required maxlength="32" autocomplete="false">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_city">City</label>
                        <input type="text" class="form-control" id="inp_city" placeholder="City"
                               maxlength="128" autocomplete="false">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_address">Address</label>
                        <textarea class="form-control" rows="1" id="inp_address" placeholder=""
                                  maxlength="256" autocomplete="false"></textarea>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_email">Email</label>
                        <input type="email" class="form-control" id="inp_email" placeholder="Email"
                               maxlength="64" autocomplete="false">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_tel_No">Tel No</label>
                        <input type="text" class="form-control" id="inp_tel_No" placeholder="Tel No"
                               maxlength="20" autocomplete="false">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_hp_No">HP No</label>
                        <input type="text" class="form-control" id="inp_hp_No" placeholder="HP No"
                               maxlength="20" autocomplete="false">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_permission">Permission</label>
                        <select id="inp_permission" class="form-control">
                            <option value="0">User</option>
                            <option value="1">Admin</option>
                            <option value="2">Manager</option>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inp_ManagerID">Manager ID</label>
                        <input type="text" class="form-control" id="inp_ManagerID" placeholder="Manager ID"
                               maxlength="32" autocomplete="false">
                    </div>
                </div>
                <div class="btn-toolbar" role="toolbar">
                    <div class="btn-group mr-2" role="group">
                        <button id="btn_save_user" class="btn btn-primary" type="submit"><i class="fas fa-save"></i> Save</button>
                    </div>
                    <div class="btn-group mr-2" role="group">
                        <button class="btn btn-primary" type="button" onclick="location.href = 'userlist'"><i class="fas fa-ban"></i> Cancel</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</t:wrapper>

<c:url value="/resources/static/js/adduser.js" var="adduserjs"/>
<script src="${adduserjs}" type="text/javascript"></script>
</body>
</html>
