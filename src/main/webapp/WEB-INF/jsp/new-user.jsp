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

            <form id="formAddUser">
                <div class="form-row">
					<!-- Store Name -->
					<div class="form-group col-md-2">
                 	</div>
                    <div class="form-group col-md-6">
                        <label for="inp_storeName">Store Name</label>
                        <input type="text" class="form-control" id="storeName" placeholder="Store Name" required
                               maxlength="128" autocomplete="false">
                    </div>
                 </div>
                 
                 <div class="form-row">
                 	<div class="form-group col-md-2">
                 	</div>
                    <div class="form-group col-md-4">
                    	<label for="userName">User Name</label>
                        <input type="text" class="form-control" id="userName" placeholder="User Name" required
                               maxlength="128" autocomplete="false">
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label for="userId">User ID</label>
                        <input type="text" class="form-control" id="userId" placeholder="User ID" required
                               maxlength="128" autocomplete="false">
                    </div>
                 </div>
                 
                 <div class="form-row">
                 	<div class="form-group col-md-2">
                 	</div>
                    <div class="form-group col-md-4">
                    	<label for="password">New Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Password" required
                               maxlength="128" autocomplete="false">
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label for="confirmPassword">Confirm New Password</label>
                        <input type="password" class="form-control" id="confirmPpassword" placeholder="Confirm New Password" required
                               maxlength="128" autocomplete="false">
                    </div>
                 </div>
                 
                 <div class="form-row">
                 	<div class="form-group col-md-2">
                 	</div>
                    <div class="form-group col-md-4">
                        <label for="countryCode">Country</label>
                        <select id="countryCode" class="form-control">
                            <option value="" disabled selected>Select your option</option>
                            <option value="KN">NORTH KOREA (KN)</option>
                            <option value="KR">KOREA (KR)</option>
                            <option value="KV">KOSOVO (KV)</option>
                            <option value="KW">KUWAIT (KW)</option>
                            <option value="LA">LAOS (LA)</option>
                            <option value="LB">LEBANON (LB)</option>
                        </select>
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label for="city">City</label>
                        <input type="text" class="form-control" id="city" placeholder="City"
                               maxlength="128" autocomplete="false">
                    </div>
                 </div>
                 <div class="form-row">
                 	<div class="form-group col-md-2">
                 	</div>
                 	<div class="form-group col-md-6">
                        <label for="address">Address</label>
                        <textarea class="form-control" rows="3" id="address" placeholder="Address"
                                  maxlength="256" autocomplete="false"></textarea>
                    </div>
                 </div>
                 
                 <div class="form-row">
                 	<div class="form-group col-md-2">
                 	</div>
                 	<div class="form-group col-md-4">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" id="email" placeholder="Email"
                               maxlength="128" autocomplete="false">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="telNo">Tel No.</label>
                        <input type="text" class="form-control" id="telNo" placeholder="Tel No."
                               maxlength="128" autocomplete="false">
                    </div>
                 </div>
                 
                 <div class="form-row">
                 	<div class="form-group col-md-2">
                 	</div>
                 	<div class="form-group col-md-4">
                        <label for="hpNo">HP No.</label>
                        <input type="text" class="form-control" id="hpNo" placeholder="HP No."
                               maxlength="128" autocomplete="false">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="managerSequence">Manager ID</label>
                        <input type="text" class="form-control" id="managerSequence" placeholder="Manager ID"
                               maxlength="32" autocomplete="false">
                    </div>
                 </div>
                 <div class="form-row">
                 	<div class="form-group col-md-2">
                 	</div>
                 	<div class="form-group col-md-4">
                 		<label for="permission">Permission</label>
                 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 		<input type="radio" name="permission" value="1"> Admin &nbsp;&nbsp;
                 		<input type="radio" name="permission" value="2"> Manager &nbsp;&nbsp;
                 		<input type="radio" name="permission" value="0"> User
           			</div>
        		 </div>
                 <div class="form-row">
                	<div class="form-group col-md-2">
                 	</div>
                 	<div class="form-group col-md-4">
                 		<div class="btn-group mr-2" role="group">
                        <button id="btn_save_user" class="btn btn-primary" type="submit"><i class="fas fa-save"></i> Save</button>
                    </div>
                    <div class="btn-group mr-2" role="group">
                        <button class="btn btn-primary" type="button" onclick="location.href = 'user-management'"><i class="fas fa-ban"></i> Cancel</button>
                    </div>
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
