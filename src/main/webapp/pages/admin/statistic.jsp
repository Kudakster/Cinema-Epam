<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${sessionScope.get('locale')}">
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/selectNumberMovie.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="d-flex align-items-start justify-content-start position-relative">
    <h3><fmt:message key="label.ticketsSold"/></h3>
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="label.auditorium"/></th>
            <th scope="col"><fmt:message key="label.ticketsPerDay"/></th>
            <th scope="col"><fmt:message key="label.ticketsPerWeek"/></th>
            <th scope="col"><fmt:message key="label.ticketsPerMonth"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${sessionScope.auditorium.auditoriumName}</td>
            <td>${requestScope.ticketsSoldForTheDay}</td>
            <td>${requestScope.ticketsSoldForThePastWeek}</td>
            <td>${requestScope.ticketsSoldForThePastMonth}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
