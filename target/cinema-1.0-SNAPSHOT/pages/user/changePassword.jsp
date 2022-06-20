<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="title" value="Change Password" scope="page" />
<c:set var="pageName" value="changePassword" scope="page" />

<%@ include file="/pages/parts/header.jsp" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/changePassword.css" rel="stylesheet">
</head>
<body>
<!-- change password -->
<div class="col-xxl-6 margin-header">
    <div class="bg-secondary-soft px-4 py-5 rounded">
        <div class="row g-3">
            <h4 class="my-4">Change Password</h4>
            <!-- Old password -->
            <div class="col-md-6">
                <label for="exampleInputPassword1" class="form-label">Old password *</label>
                <input type="password" class="form-control" id="exampleInputPassword1">
            </div>
            <!-- New password -->
            <div class="col-md-6">
                <label for="exampleInputPassword2" class="form-label">New password *</label>
                <input type="password" class="form-control" id="exampleInputPassword2">
            </div>
            <!-- Confirm password -->
            <div class="col-md-12">
                <label for="exampleInputPassword3" class="form-label">Confirm Password *</label>
                <input type="password" class="form-control" id="exampleInputPassword3">
            </div>
        </div>
    </div>
</div>
</body>
</html>
