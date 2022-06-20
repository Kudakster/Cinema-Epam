<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="title" value="Registration" scope="page" />
<c:set var="pageName" value="sign-up" scope="page" />

<%@ include file="/pages/parts/header.jsp" %>
<!doctype html>
<html lang="${sessionScope.get('locale')}">
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/sign-up.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/validateLogin.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/setFormStatus.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/sign-up.css" rel="stylesheet">
</head>
<body>
<div class="container mt-3 margin-header">
    <h3 class="align-items-center">Registration form</h3>

    <form action="${pageContext.request.contextPath}/cinema/sign-up" method="post" id="basic-form">

        <div class="mb-3 mt-3" id="login-form">
                <label for="login" class="form-label">Login:</label>
                <input type="text" class="form-control" id="login" placeholder="Enter username" name="login"
                maxlength="30">
        </div>

        <div class="mb-3" id="pwd-form">
            <label for="pwd" class="form-label">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password"
                   maxlength="30">
        </div>

        <div class="mb-3" id="confirm-password-form">
            <label for="confirm-password" class="form-label">Confirm Password:</label>
            <input type="password" class="form-control" id="confirm-password" placeholder="Enter password" name="confirm-password">
        </div>

        <div class="mb-3 mt-3" id="firstname-form">
            <label for="firstname" class="form-label">Firstname:</label>
            <input type="text" class="form-control" id="firstname" placeholder="Enter Firstname" name="firstname"
                   maxlength="30">
        </div>

        <div class="mb-3 mt-3" id="surname-form">
            <label for="surname" class="form-label">Surname:</label>
            <input type="text" class="form-control" id="surname" placeholder="Enter Surname" name="surname"
                   maxlength="30">
        </div>

        <div class="mb-3 mt-3" id="email-form">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
        </div>

        <div class="mb-3 mt-3" id="phone-number-form">
            <label for="phone-number" class="form-label">Phone-number:</label>
            <input type="tel" class="form-control" id="phone-number" placeholder="Enter phone-number"
                   name="phone-number"
                   value="+380" maxlength="13">
        </div>

        <div class="error-input">
            <p>${requestScope.error.toString()}</p>
        </div>

        <button type="submit" class="btn btn-primary" id="submit-button" disabled>Submit</button>
    </form>
</div>
</body>
</html>
