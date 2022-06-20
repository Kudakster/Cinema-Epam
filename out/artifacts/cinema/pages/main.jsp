<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="title" value="Cinema" scope="page"/>
<c:set var="pageName" value="main" scope="page"/>

<%@ include file="/pages/parts/header.jsp" %>
<!doctype html>
<html>
<head>
    <title>Cinema</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/carousel/">


    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>
<!-- The video -->
<div class="top-movie-container">
    <div class="video-container">
        <iframe src="https://www.youtube.com/embed/giXco2jaZ_4?version=3&autoplay=1&loop=1&cc_load_policy=3&mute=1&controls=0&iv_load_policy=3&playlist=giXco2jaZ_4"
                allowfullscreen></iframe>
    </div>
    <div class="text-container">
        <span class="top-movie-text">
            After more than thirty years of service as one of the Navy's top aviators, Pete Mitchell is where he belongs, pushing the envelope as a courageous test pilot and dodging the advancement in rank that would ground him.
        </span>
    </div>
</div>

</body>
</html>