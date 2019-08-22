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
            <a class="nav-link" href="/">
                <span>Users</span>
            </a>
        </li> -->

        <!-- <li class="nav-item active">
            <a class="nav-link" href="devices">
                <span>Devices</span>
            </a>
        </li> -->
        <!-- <li class="nav-item active">
            <a class="nav-link" href="contents">
                <span>Video & Background Image</span>
            </a>
        </li> -->
        <li class="nav-item active">
        	<!-- <button type="button" class="btn btn-info" href="products">
        		<span>Recommended Product</span>
        	</button> -->
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
            <!--
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="devices">Devices List</a>
                </li>
            </ol>
            -->
			<div class="container mt-3">
            <form id="formProducts" method="post">
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="oily" name="filename">
            		<label class="custom-file-label" for="file">Oily</label>
            	</div>
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="good" name="filename">
            		<label class="custom-file-label" for="file">Good</label>
            	</div>
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="dry" name="filename">
            		<label class="custom-file-label" for="file">Dry</label>
            	</div>
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="complexion" name="filename">
            		<label class="custom-file-label" for="file">Complexion</label>
            	</div>
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="wrinkles" name="filename">
            		<label class="custom-file-label" for="file">Wrinkles</label>
            	</div>
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="impurities" name="filename">
            		<label class="custom-file-label" for="file">Impurities</label>
            	</div>
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="keratin" name="filename">
            		<label class="custom-file-label" for="file">Keratin</label>
            	</div>
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="moisture" name="filename">
            		<label class="custom-file-label" for="file">Moisture</label>
            	</div>
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="pores" name="filename">
            		<label class="custom-file-label" for="file">Pores</label>
            	</div>
            	<div class="custom-file mb-3">
            		<input type="file" class="custom-file-input" id="spots" name="filename">
            		<label class="custom-file-label" for="file">Spots</label>
            	</div>
	        	<br/><br/>
                <div class="btn-toolbar" role="toolbar">
                    <div class="btn-group mr-2" role="group">
                        <button id="btn_save_device" class="btn btn-primary" type="submit">
                        	<i class="fas fa-save"></i>
                            Save
                        </button>
                    </div>
                </div>
            </form>
        	</div>
        </div>
    </div>
</t:wrapper>

<c:url value="/resources/static/js/products.js" var="products"/>
<script src="${products}" type="text/javascript"></script>
</body>
</html>
