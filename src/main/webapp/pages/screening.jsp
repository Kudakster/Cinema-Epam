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
    <title>Screening</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/carousel/">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/cinema.css" rel="stylesheet">

</head>
<body>
<div class="margin-header w-100 h-100">
    <form action="${pageContext.request.contextPath}/cinema/buy-ticket" method="post">
    <c:forEach items="${requestScope.rows}" var="row">
        <h4>${row.toString()}</h4>
        <select name="seatID" multiple>
            <c:forEach items="${requestScope.seatsAvailable}" var="seat">
                <c:choose>
                    <c:when test="${row == seat.key.seatRow && seat.value == true}">
                        <option value="${seat.key.seatID}">${seat.key.seatNumber}</option>
                    </c:when>
                </c:choose>
            </c:forEach>
        </select>
    </c:forEach>
        <button type="submit"><fmt:message key="button.buyTicket"/></button>
    </form>
</div>
</body>

