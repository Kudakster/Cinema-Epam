<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.URLDecoder"%>

<fmt:setLocale value="${cookie['cookieLocale'].value}"/>
<fmt:setBundle basename="messages"/>

${pageContext.ELContext.importHandler.importClass('java.net.URLEncoder')}

<!doctype html>
<html lang="${cookie['cookieLocale'].value}">
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/cinema-logo.svg"/>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/sign-in.css" rel="stylesheet">
</head>
<body class="text-center">

<main class="form-signin">
    <form method="get" action="${pageContext.request.contextPath}/cinema/main">
        <button type="submit">
            <img class="mb-4" src="${pageContext.request.contextPath}/image/cinema-logo.svg" width="57" height="57"
                 alt="Cinema Logo">
        </button>
    </form>

    <form method="post" action="${pageContext.request.contextPath}/cinema/sign-in">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
                <input type="text" class="form-control" name="login" id="floatingInput" placeholder="Login"
                       value="">
            <label for="floatingInput" value="">Login</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="password" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
        </div>

        <div class="error-input">
            <c:if test="${not empty cookie.error}">
                <p>${URLDecoder.decode(cookie.error.value, 'UTF-8')}</p>
            </c:if>
        </div>

        <button class="w-100 btn btn-lg btn-primary">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
    </form>
</main>
</body>
