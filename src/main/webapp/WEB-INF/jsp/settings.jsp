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

        <li class="nav-item">
            <a class="nav-link" href="devices">
                <span>Devices</span>
            </a>
        </li>

        <li class="nav-item active">
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
                    <a href="settings">Settings</a>
                </li>
            </ol>

            <form id="form_addUser" action="savesettings" method="post">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inp_serverURL">Server URL</label>
                        <input type="text" class="form-control" id="inp_serverURL" placeholder="Server URL" required
                               maxlength="256" name="server_ul">
                    </div>
                </div>
                <div class="btn-toolbar" role="toolbar">
                    <div class="btn-group mr-2" role="group">
                        <button id="btn_save_settings" class="btn btn-primary" type="submit"><i class="fas fa-save"></i>
                            Save
                        </button>
                    </div>
                    <div class="btn-group mr-2" role="group">
                        <button class="btn btn-primary" type="button" onclick="location.href = 'userlist'"><i
                                class="fas fa-ban"></i> Cancel
                        </button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</t:wrapper>

</body>
</html>
