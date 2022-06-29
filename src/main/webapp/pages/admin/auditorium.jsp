<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="${sessionScope.get('locale')}">
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/addRow.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/auditorium.css" rel="stylesheet">

</head>
<body>
<div class="d-block align-items-start justify-content-start position-relative">
    <div class="d-flex justify-content-center w-100 mb-4">
        <div class="d-flex w-auto">
            <div class="text-center">
                <form action="${pageContext.request.contextPath}/cinema/update-auditorium" method="post">
                    <input class="input-name" type="text" name="name" id="name"
                           value="${sessionScope.auditorium.auditoriumName}">
                    <button type="submit" class="button-redact" id="button-redact">
                        <img class="redact-icon" src="${pageContext.request.contextPath}/image/icon-check.svg"
                             width="15" height="15" alt="Confirm">
                    </button>
                </form>
            </div>
        </div>
    </div>
    <div class="d-flex justify-content-center">
        <form action="${pageContext.request.contextPath}/cinema/update-seats" method="post">
            <table class="table-seat">
                <tbody id="auditorium-seats">
                <c:choose>
                    <c:when test="${requestScope.seats == null}">
                        <tr>
                            <th><input class="input-seat" type="number" name="seat-number" min="0"></th>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${requestScope.seats}" var="seat">
                            <tr>
                                <th><input class="input-seat" type="number" name="seat-number" min="0"
                                           value="${seat.value}"></th>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <div class="button-container">
                <button type="button" name="add-row-button" class="btn btn-primary"><fmt:message key="button.addRow"/></button>
                <button class="btn btn-primary"><fmt:message key="button.save"/></button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
