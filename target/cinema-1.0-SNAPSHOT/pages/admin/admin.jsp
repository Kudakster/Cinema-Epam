<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="title" value="Administration" scope="page"/>

<%@ include file="/pages/parts/header.jsp" %>
<html lang="${sessionScope.get('locale')}">
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/openSideBarPage.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/getCookie.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/movie.css" rel="stylesheet">
</head>
<body>
<div class="d-flex align-items-start">
    <div class="justify-content-center position-fixed d-inline-block vh-100 min-vw-180-px bg-black">
        <div class="flex-box flex-wrap mt-7">
            <div class="text-start text-white ps-2 my-2">
                <a class="section-reference" href="#movie" id="movie">Movie</a>
            </div>
            <ul class="justify-content-center rounded mx-4">
                <li><a class="header-reference mb-3 bg-dark rounded-3" href="#a-movie" id="a-movie"><fmt:message key="label.addMovie"/></a>
                </li>
                <li><a class="header-reference mb-3 bg-dark rounded-3" href="#u-schedule" id="u-schedule"><fmt:message key="label.schedule"/></a>
                </li>
            </ul>

            <div class="text-start text-white ps-2 my-2">
                <a class="section-reference" href="#auditorium" id="auditorium"><fmt:message key="label.auditorium"/></a>
            </div>
            <ul class="justify-content-center rounded mx-4">
                <li><a class="header-reference mb-3 bg-dark rounded-3" href="#statistic" id="statistic"><fmt:message key="label.statistic"/></a>
                </li>
            </ul>
        </div>
    </div>
    <div class="d-inline-block margin-header margin-side-bar">
        <div class="w-100 align-content-center error-input">
            <h3 class="error-screening">${cookie.get("error").value}</h3>
        </div>
        <div class="w-100" id="content"></div>
    </div>
</div>
</body>
</html>
