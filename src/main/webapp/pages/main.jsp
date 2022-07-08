<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="Cinema" scope="page"/>
<c:set var="pageName" value="main" scope="page"/>
<fmt:setLocale value="${sessionScope.get('locale')}"/>
<fmt:setBundle basename="messages"/>

<%@ include file="/pages/parts/header.jsp" %>
<html lang="${sessionScope.get('locale')}">
<head>
    <title>Cinema</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/carousel/">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/slider.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/movie.css" rel="stylesheet">
</head>
<body>
<div class="d-flex align-items-start justify-content-start margin-header">
    <div class="d-block align-items-center justify-content-center w-100 flex-wrap d-flex" id="container">
        <c:forEach items="${requestScope.screenings}" var="item">
            <div class="rectangle" hidden>
                <div class="poster">
                    <img class="movie-image" src="${pageContext.request.contextPath}${item.key.imgURL}" alt="">
                </div>
                <div class="image-overflow">
                    <form action="${pageContext.request.contextPath}/cinema/u-movie" method="get">
                        <div class="image-overflow-content top">
                            <span name="u-movie-id">${item.key.name}</span>
                        </div>
                    </form>
                    <c:forEach items="${item.value}" var="screening">
                        <form action="${pageContext.request.contextPath}/cinema/screening" method="get">
                            <input name="screening-id" value="${screening.screeningID}" hidden>
                            <div class="image-overflow-content">
                                <button type="submit">
                                    <span name="screening-start-time">${screening.startTime.toLocalTime()}</span>
                                </button>
                            </div>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<button type="button" class="btn-left" id="previous" hidden>
    <img src="${pageContext.request.contextPath}/image/icon-left.svg" width="60" height="105">
</button>
<button type="button" class="btn-right" id="next" hidden>
    <img src="${pageContext.request.contextPath}/image/icon-right.svg" width="60" height="105">
</button>

<%--<div class="top-movie-container">--%>
<%--    <div class="video-container">--%>
<%--        <iframe src="https://www.youtube.com/embed/giXco2jaZ_4?version=3&autoplay=1&loop=1&cc_load_policy=3&mute=1&controls=0&iv_load_policy=3&playlist=giXco2jaZ_4"--%>
<%--                allowfullscreen></iframe>--%>
<%--    </div>--%>
<%--    <div class="text-container">--%>
<%--        <span class="top-movie-text">--%>
<%--            After more than thirty years of service as one of the Navy's top aviators, Pete Mitchell is where he belongs, pushing the envelope as a courageous test pilot and dodging the advancement in rank that would ground him.--%>
<%--        </span>--%>
<%--    </div>--%>
<%--</div>--%>

</body>
</html>