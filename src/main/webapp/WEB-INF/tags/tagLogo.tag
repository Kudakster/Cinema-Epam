<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${pageName == 'main'}">
        <img class="button-logo" src="${pageContext.request.contextPath}/image/cinema-logo.svg"
             width="48"
             height="48" alt="Cinema Logo">
    </c:when>
    <c:otherwise>
        <form class="text-end" method="get" action="${pageContext.request.contextPath}/cinema/main">
            <button class="button-logo" type="submit">
                <img src="${pageContext.request.contextPath}/image/cinema-logo.svg"
                     width="48"
                     height="48" alt="Cinema Logo">
            </button>
        </form>
    </c:otherwise>
</c:choose>