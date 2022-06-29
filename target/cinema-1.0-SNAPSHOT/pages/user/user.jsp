<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="title" value="MyProfile" scope="page"/>

<%@ include file="/pages/parts/header.jsp" %>
<fmt:setLocale value="${sessionScope.get('locale')}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html lang="${sessionScope.get('locale')}">
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/validateLogin.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/setFormStatus.js" type="text/javascript"></script>

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/user.css" rel="stylesheet">
</head>
<body>
<div class="d-flex align-items-start margin-header">
    <div class="d-inline-block container mt-3 w-25 ms-5 me-0">
        <h3 class="align-items-center"><fmt:message key="label.myProfile"/></h3>

        <form action="${pageContext.request.contextPath}/cinema/update-user" method="post" id="basic-form">

            <div class="mb-3 mt-3" id="login-form">
                <label for="login" class="form-label"><fmt:message key="label.login"/></label>
                <input type="text" class="form-control" id="login" data-val="${sessionScope.user.login}" placeholder="<fmt:message key="placeholder.enter.login"/>" name="login"
                       maxlength="30" value=${sessionScope.user.login}>
            </div>

            <div class="mb-3 mt-3" id="firstname-form">
                <label for="firstname" class="form-label"><fmt:message key="label.firstname"/></label>
                <input type="text" class="form-control" id="firstname" placeholder="<fmt:message key="placeholder.enter.firstname"/>" name="firstname"
                       maxlength="30" value=${sessionScope.user.firstName}>
            </div>

            <div class="mb-3 mt-3" id="surname-form">
                <label for="surname" class="form-label"><fmt:message key="label.surname"/></label>
                <input type="text" class="form-control" id="surname" placeholder="<fmt:message key="placeholder.enter.surname"/>" name="surname"
                       maxlength="30" value=${sessionScope.user.surName}>
            </div>

            <div class="mb-3 mt-3" id="email-form">
                <label for="email" class="form-label"><fmt:message key="label.email"/></label>
                <input type="email" class="form-control" id="email" placeholder="<fmt:message key="placeholder.enter.email"/>" name="email"
                       disabled value=${sessionScope.user.email}>
            </div>

            <div class="mb-3 mt-3" id="phone-number-form">
                <label for="phone-number" class="form-label"><fmt:message key="label.phoneNumber"/></label>
                <input type="text" class="form-control" id="phone-number" placeholder="<fmt:message key="placeholder.enter.phoneNumber"/>"
                       name="phone-number" maxlength="13" disabled
                       value=${sessionScope.user.phoneNumber}>
            </div>

            <button type="submit" class="btn btn-primary" id="submit-button" disabled>
                <fmt:message key="button.updateProfile"/>
            </button>
        </form>
    </div>
    <div class="d-inline-block container mt-3 w-25 ms-5 me-0">
        <h3><fmt:message key="h.tickets"/></h3>
        <c:forEach items="${requestScope.ticketList}" var="ticket">
            <span>${requestScope.auditoriumMap.get(ticket).auditoriumName}</span>
            <br>
            <span>${requestScope.movieMap.get(ticket).name}</span>
            <br>
            <span>${requestScope.screeningMap.get(ticket).date}</span>
            <br>
            <span>${requestScope.screeningMap.get(ticket).startTime}</span>
            <span>${requestScope.screeningMap.get(ticket).endTime}</span>
            <br>
            <span>Row: </span>
            <span>${requestScope.seatMap.get(ticket).seatRow}</span>
            <br>
            <span>Number: </span>
            <span>${requestScope.seatMap.get(ticket).seatNumber}</span>
            <br>
        </c:forEach>
    </div>
</div>

</body>
</html>
