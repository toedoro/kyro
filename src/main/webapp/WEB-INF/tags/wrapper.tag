<%@ tag description="main" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Custom fonts for this template-->
    <c:url value="/resources/static/fonts/fontawesome-free/css/all.min.css" var="fontawesome"/>
    <link href="${fontawesome}" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <c:url value="/resources/static/datatables/dataTables.bootstrap4.css" var="dataTablescss"/>
    <link href="${dataTablescss}" rel="stylesheet">

    <c:url value="/resources/static/datatables/select.dataTables.min.css" var="dataTablesselectcss"/>
    <link href="${dataTablesselectcss}" rel="stylesheet">

    <!-- Custom styles for this template-->
    <c:url value="/resources/static/css/sb-admin.css" var="sbadmincss"/>
    <link href="${sbadmincss}" rel="stylesheet">

    <c:url value="/resources/static/css/bootstrap4-toggle.min.css" var="toggle"/>
    <link href="${toggle}" rel="stylesheet" type="text/css">

    <c:url value="/resources/static/css/wrapper.css" var="wrapper"/>
    <link href="${wrapper}" rel="stylesheet" type="text/css">

    <c:url value="/resources/static/css/bootstrap-datetimepicker.min.css" var="datepicker"/>
    <link href="${datepicker}" rel="stylesheet" type="text/css">

    <c:url value="/resources/static/css/jquery-ui.css" var="jqueryuicss"/>
    <link href="${jqueryuicss}" rel="stylesheet" type="text/css">

    <title>Kyro</title>
</head>

<body id="page-top">

<nav class="navbar navbar-expand navbar-dark bg-dark static-top">
    <a class="navbar-brand mr-1" href="userlist"><img src="<c:url value="/resources/static/img/KYRO.png" />" alt="KYRO"></a>

    <!-- Navbar -->
    <ul class="navbar-nav ml-auto">
        <div class="div-box">
            <label class="label-style nav-link">ADMIN</label>
        </div>
        <a class="label-style link-pad nav-link" href="/logout" >LOG OUT</a>
    </ul>
</nav>

<div id="wrapper">
    <jsp:doBody/>

    <!-- Sticky Footer -->
    <footer class="sticky-footer">
        <div class="container my-auto">
            <div class="copyright text-center my-auto">
                <span>Copyright © Kyro 2019</span>
            </div>
        </div>
    </footer>
    <!-- /.content-wrapper -->
</div>
<!-- /#wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<c:url value="/resources/static/jquery/jquery.min.js" var="jquery"/>
<script src="${jquery}" type="text/javascript"></script>

<c:url value="/resources/static/bootstrap/js/bootstrap.bundle.min.js" var="bootstrapbundle"/>
<script src="${bootstrapbundle}" type="text/javascript"></script>

<!-- Core plugin JavaScript-->
<c:url value="/resources/static/jquery-easing/jquery.easing.min.js" var="jqueryeasing"/>
<script src="${jqueryeasing}" type="text/javascript"></script>

<!-- Page level plugin JavaScript-->
<c:url value="/resources/static/datatables/jquery.dataTables.js" var="dataTablesjs"/>
<script src="${dataTablesjs}" type="text/javascript"></script>

<c:url value="/resources/static/datatables/dataTables.bootstrap4.js" var="bootstrap4js"/>
<script src="${bootstrap4js}" type="text/javascript"></script>

<c:url value="/resources/static/datatables/dataTables.select.min.js" var="select"/>
<script src="${select}" type="text/javascript"></script>

<c:url value="/resources/static/js/bootstrap4-toggle.min.js" var="togglejs"/>
<script src="${togglejs}" type="text/javascript"></script>

<c:url value="/resources/static/js/bootstrap-datetimepicker.min.js" var="datepickerjs"/>
<script src="${datepickerjs}" type="text/javascript"></script>

<!-- Custom scripts for all pages-->
<c:url value="/resources/static/js/sb-admin.min.js" var="sbadmin"/>
<script src="${sbadmin}" type="text/javascript"></script>

<c:url value="/resources/static/js/jquery-ui.js" var="jqueryuijs"/>
<script src="${jqueryuijs}" type="text/javascript"></script>

<c:url value="/resources/static/js/notify.js" var="notifyjs"/>
<script src="${notifyjs}" type="text/javascript"></script>

</body>
</html>
