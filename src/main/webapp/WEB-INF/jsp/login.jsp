<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <c:url value="/resources/static/css/login.css" var="logincss"/>
    <link href="${logincss}" rel="stylesheet" type="text/css">

    <c:url value="/resources/static/fonts/fontawesome-free/css/all.css" var="fontawesome"/>
    <link href="${fontawesome}" rel="stylesheet" type="text/css">

    <c:url value="/resources/static/css/sb-admin.css" var="sbadmincss"/>
    <link href="${sbadmincss}" rel="stylesheet" type="text/css">



    <title>Kyro - Login</title>
</head>
<body class="bg-dark">
<div class="container">

    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
            <form action="/login" method="post">
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="input_userID" class="form-control" placeholder="User ID"
                               required="required" autofocus="autofocus" name="username" autocomplete="false">
                        <label for="input_userID">User ID</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="input_Password" class="form-control" placeholder="Password"
                               required="required" name="password">
                        <label for="input_Password">Password</label>
                    </div>
                </div>
                <button class="btn btn-primary btn-block" type="submit">Login</button>
            </form>
        </div>
    </div>
</div>


<c:url value="/resources/static/jquery/jquery.js" var="jquery"/>
<script src="${jquery}" type="application/javascript"></script>

<c:url value="/resources/static/bootstrap/js/bootstrap.bundle.js" var="bootstrapbundle"/>
<script src="${bootstrapbundle}" type="application/javascript"></script>

<c:url value="/resources/static/jquery-easing/jquery.easing.js" var="jqueryeasing"/>
<script src="${jqueryeasing}" type="application/javascript"></script>
</body>
</html>
