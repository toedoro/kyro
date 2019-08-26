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
            <!-- 
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="userlist">User List</a>
                </li>
            </ol> 
            -->

            <form id="formUserDetails">
           		<div class="form-row">
           			<div class="form-group col-md-2">
           			</div>
                    <div class="form-group col-md-6">
                        <label for="inp_storeName">Store Name</label>
                        <input type="text" class="form-control" id="storeName" placeholder="Store Name" 
                        		value="${user.storeName}" required maxlength="128" autocomplete="false" disabled>
                    </div>
                    <div class="form-group col-md-2">
                    	<label for="inp_storeName">&nbsp;</label>
                        <input id="chkToggle1" type="checkbox" checked data-toggle="toggle" data-on="Edit" data-off="Cancel" data-onstyle="success" data-offstyle="danger">
                    </div>
                </div>
                
                <div class="form-row">
                	<div class="form-group col-md-2">
           			</div>
                    <div class="form-group col-md-4">
                    	<label for="userName">User Name</label>
                        <input type="text" class="form-control" id="userName" placeholder="User Name" 
                        		required value="${user.userName}" maxlength="128" autocomplete="false" disabled>
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label for="userId">User ID</label>
                        <input type="text" class="form-control" id="userId" placeholder="User ID" 
                        		required value="${user.userId}" maxlength="128" autocomplete="false" disabled>
                    </div>
                 </div>
                 
                 <div class="form-row">
                 	<div class="form-group col-md-2">
           			</div>
                    <div class="form-group col-md-4">
                        <label for="countryCode">Country</label>
                        <select id="countryCode" class="form-control" disabled value="${user.countryCode}">
                            <option value="" disabled selected>Select your option</option>
                            <option value="KN" ${user.countryCode == 'KN' ? 'selected' :''}>NORTH KOREA (KN)</option>
                            <option value="KR" ${user.countryCode == 'KR' ? 'selected' :''}>KOREA (KR)</option>
                            <option value="KV" ${user.countryCode == 'KV' ? 'selected' :''}>KOSOVO (KV)</option>
                            <option value="KW" ${user.countryCode == 'KW' ? 'selected' :''}>KUWAIT (KW)</option>
                            <option value="LA" ${user.countryCode == 'LA' ? 'selected' :''}>LAOS (LA)</option>
                            <option value="LB" ${user.countryCode == 'LB' ? 'selected' :''}>LEBANON (LB)</option>
                            <option value="PH" ${user.countryCode == 'PH' ? 'selected' :''}>PHILIPPINES (PH)</option>
                        </select>
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label for="city">City</label>
                        <input type="text" class="form-control" id="city" placeholder="City"
                               value="${user.city}" maxlength="128" autocomplete="false" disabled>
                    </div>
                 </div>
                 <div class="form-row">
                 	<div class="form-group col-md-2">
           			</div>
                 	<div class="form-group col-md-6">
                        <label for="address">Address</label>
                        <textarea class="form-control" rows="3" id="address" placeholder="Address"
                                  maxlength="256" autocomplete="false" disabled>${user.address}
                        </textarea>
                    </div>
                 </div>
                 
                 <div class="form-row">
                 	<div class="form-group col-md-2">
           			</div>
                 	<div class="form-group col-md-4">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" id="email" placeholder="Email"
                               value="${user.email}" maxlength="128" autocomplete="false" disabled>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="telNo">Tel No.</label>
                        <input type="text" class="form-control" id="telNo" placeholder="Tel No."
                               value="${user.telNo}" maxlength="128" autocomplete="false" disabled>
                    </div>
                 </div>
                 
                 <div class="form-row">
                 	<div class="form-group col-md-2">
           			</div>
                 	<div class="form-group col-md-4">
                        <label for="hpNo">HP No.</label>
                        <input type="text" class="form-control" id="hpNo" placeholder="HP No."
                               value="${user.hpNo}" maxlength="128" autocomplete="false" disabled>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="managerSequence">Manager ID</label>
                        <input type="text" class="form-control" id="managerSequence" placeholder="Manager ID"
                               value="${user.manager.sequence}" maxlength="32" autocomplete="false" disabled>
                    </div>
                 </div>
                 <div class="form-row">
                 	<div class="form-group col-md-2">
                 	</div>
                 	<div class="form-group col-md-4">
                 		<label for="permission">Permission</label>
                 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 		<input type="radio" name="permission" value="1" ${user.permission == 1 ? 'checked' :''}> Admin &nbsp;&nbsp;
                 		<input type="radio" name="permission" value="2" ${user.permission == 2 ? 'checked' :''}> Manager &nbsp;&nbsp;
                 		<input type="radio" name="permission" value="0" ${user.permission == 0 ? 'checked' :''}> User
           			</div>
          		</div>
                <div class="form-row">
                	<div class="form-group col-md-2">
                 	</div>
                 	<div class="form-group col-md-4">
	                    <div class="btn-group mr-2" role="group">
	                        <button id="btn_save_user_edit" class="btn btn-primary" type="submit" disabled><i class="fas fa-save"></i> Save</button>
	                    </div>
	                    <div class="btn-group mr-2" role="group">
	                        <button class="btn btn-primary" type="button" onclick="location.href = 'user-management'"><i class="fas fa-ban"></i> Cancel</button>
	                    </div>
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
	                                <th>App Version</th>
	                                <th>Registration date</th>
	                            </tr>
                            </thead>
                            <tbody>
	                            <c:forEach var="device" items="${user.devices}">
	                            <tr>
	                                <td></td>
	                                <td hidden>${device.sequence}</td>
	                                <td>${device.opticalNumber}</td>
	                                <td>${device.appVersion}</td>
	                                <td>${device.registrationDate}</td>
	                            </tr>
	                            </c:forEach>
                            </tbody>
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
