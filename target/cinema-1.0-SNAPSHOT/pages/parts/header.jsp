<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.get('locale')}"/>
<fmt:setBundle basename="messages"/>

<!doctype html>
<html lang="${sessionScope.get('locale')}">
<head>
    <title>${pageScope.get("title")}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/cinema-logo.svg"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/getCookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/selectLang.js" type="text/javascript"></script>


    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <style>
        {
            font-size: 1.125rem
        ;
            text-anchor: middle
        ;
            -webkit-user-select: none
        ;
            -moz-user-select: none
        ;
            user-select: none
        ;
        }

        @media (min-width: 768px) { {
            font-size: 3.5rem;
        }
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/headers.css" rel="stylesheet">

</head>
<body>
<header class="px-3 bg-dark text-white">
    <div class="container-header">
        <div class="d-flex flex-wrap align-items-center justify-content-center-max-620">
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

            <ul class="nav justify-content-start mb-md-0 ht-1" id="ref">
                <li><a href="${pageContext.request.contextPath}/cinema/main"
                       class="header-reference"><fmt:message key="label.main"/></a>
                </li>
                <li><a href="#" class="header-reference"><fmt:message key="label.about"/></a>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.user.userRole.toString() == 'ADMIN'}">
                        <li>
                            <form class="text-end" method="get"
                                  action="${pageContext.request.contextPath}/cinema/admin">
                                <button type="submit" class="header-reference"><fmt:message
                                        key="label.administration"/></button>
                            </form>
                        </li>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </ul>


            <div class="nav ht-1 w-50 ml-auto justify-content-end align-content-center">
                <c:if test="${pageName == 'main'}">
                    <ul class="nav justify-content-start mb-md-0 ht-1 me-2">
                        <li>
                            <form action="${pageContext.request.contextPath}/cinema/main">
                                <select name="date" class="header-reference" onchange="this.form.submit()">Date
                                    <option>Date:</option>
                                    <c:forEach items="${requestScope.dates}" var="date">
                                        <option value="${date}">${date}</option>
                                    </c:forEach>
                                </select>
                            </form>
                        </li>
                    </ul>
                </c:if>

<%--                <form class="w-25">--%>
<%--                    <input type="search" class="form-control form-control-dark"--%>
<%--                           placeholder="<fmt:message key="input.search"/>"--%>
<%--                           aria-label="<fmt:message key="label.search"/>">--%>
<%--                </form>--%>

                <c:choose>
                    <c:when test="${sessionScope.user.userRole.toString() == 'USER' ||
                        sessionScope.user.userRole.toString() == 'ADMIN'}">
                        <div class="dropdown mx-4 flex-div-center">
                            <a class="dropbtn flex-item-center">
                                <img src="${pageContext.request.contextPath}/image/default-user-icon.svg"
                                     width="30" height="30" alt="logo"
                                     class="rounded-circle">
                            </a>


                            <div class="dropdown-content">
                                <form method="get" action="${pageContext.request.contextPath}/cinema/user">
                                    <button type="submit" class="dropdown-button"><fmt:message
                                            key="label.myProfile"/></button>
                                </form>
                                <form method="get" action="${pageContext.request.contextPath}/cinema/logout">
                                    <button type="submit" class="dropdown-button"><fmt:message
                                            key="label.logout"/></button>
                                </form>
                            </div>

                        </div>
                    </c:when>
                    <c:otherwise>
                        <form method="get" action="${pageContext.request.contextPath}/cinema/login">
                            <button type="submit" class="btn btn-outline-light mx-2"><fmt:message
                                    key="label.login"/></button>
                        </form>
                        <c:choose>
                            <c:when test="${pageName == 'sign-up'}">
                            </c:when>
                            <c:otherwise>
                                <form method="get" action="${pageContext.request.contextPath}/cinema/registration">
                                    <button type="submit" class="btn btn-warning"><fmt:message
                                            key="label.sign-up"/></button>
                                </form>
                            </c:otherwise>
                        </c:choose>

                    </c:otherwise>
                </c:choose>

                <form id="locale-form" action="${pageContext.request.contextPath}${pageName}" method="post">
                    <c:if test="${sessionScope.get('locale') == 'en'}">
                        <select name="locale" id="select-lang" class="ms-2">
                            <option value="en"><fmt:message key="lang.en"/></option>
                            <option value="ua"><fmt:message key="lang.ua"/></option>
                        </select>
                    </c:if>
                    <c:if test="${sessionScope.get('locale') == 'ua'}">
                        <select name="locale" id="select-lang" class="ms-2">
                            <option value="ua"><fmt:message key="lang.ua"/></option>
                            <option value="en"><fmt:message key="lang.en"/></option>
                        </select>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</header>
</body>
</html>