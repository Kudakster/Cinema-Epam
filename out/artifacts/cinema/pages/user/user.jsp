<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="title" value="MyProfile" scope="page"/>

<%@ include file="/pages/parts/header.jsp" %>
<!doctype html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript"></script>

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/user.css" rel="stylesheet">
</head>
<body>
<div class="d-flex align-items-start">
    <div class="d-inline-block container mt-3 w-25 ms-5 me-0 margin-header">
        <h3 class="align-items-center">My Profile</h3>

        <form action="${pageContext.request.contextPath}/main" method="post" id="basic-form">

            <div class="mb-3 mt-3" id="login-form">
                <label for="login" class="form-label">Login:</label>
                <input type="text" class="form-control" id="login" data-val="${sessionScope.user.login}" placeholder="Enter username" name="login"
                       maxlength="30" value=${sessionScope.user.login}>
            </div>

            <div class="mb-3 mt-3" id="firstname-form">
                <label for="firstname" class="form-label">Firstname:</label>
                <input type="text" class="form-control" id="firstname" placeholder="Enter Firstname" name="firstname"
                       maxlength="30" value=${sessionScope.user.firstName}>
            </div>

            <div class="mb-3 mt-3" id="surname-form">
                <label for="surname" class="form-label">Surname:</label>
                <input type="text" class="form-control" id="surname" placeholder="Enter Surname" name="surname"
                       maxlength="30" value=${sessionScope.user.surName}>
            </div>

            <div class="mb-3 mt-3" id="email-form">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"
                       disabled value=${sessionScope.user.email}>
            </div>

            <div class="mb-3 mt-3" id="phone-number-form">
                <label for="phone-number" class="form-label">Phone-number:</label>
                <input type="text" class="form-control" id="phone-number" placeholder="Enter phone-number"
                       name="phone-number" maxlength="13" disabled
                       value=${sessionScope.user.phoneNumber}>
            </div>

            <button type="submit" class="btn btn-primary" id="submit-button" name="command" value="update-user" disabled>
                Update Profile
            </button>
        </form>
    </div>

    <div class="d-inline-block container mt-3 w-50 ms-1 me-0 margin-header-double">
        <h5 class="mb-4 mt-0">Upload your profile photo</h5>
        <div class="text-start">
            <!-- Image upload -->
            <div class="position-relative display-2 mb-3">
                <i class="fas fa-fw fa-user position-absolute top-50 start-50 translate-middle text-secondary"></i>
            </div>
            <!-- Button -->
            <input type="file" id="customFile" name="file" hidden="">
            <label class="btn btn-success-soft btn-block"
                   for="customFile">Upload</label>
            <button type="button" class="btn btn-danger-soft">Remove</button>
            <!-- Content -->
            <p class="text-muted mt-3 mb-0"><span class="me-1">Note:</span>Minimum
                size 300px x
                300px</p>
        </div>
    </div>
</div>

</body>
</html>
