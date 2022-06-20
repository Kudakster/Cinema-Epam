<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <title>${pageScope.get("title")}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/cinema-logo.svg"/>

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

<main>
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
                        <form class="text-end" method="get" action="${pageContext.request.contextPath}/main">
                            <button class="button-logo" type="submit" name="command" value="main">
                                <img src="${pageContext.request.contextPath}/image/cinema-logo.svg"
                                     width="48"
                                     height="48" alt="Cinema Logo">
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>

                <ul class="nav justify-content-start mb-md-0 ht-1" id="ref">
                    <li><a href="${pageContext.request.contextPath}/pages/main.jsp" class="header-reference">Home</a>
                    </li>
                    <li><a href="#" class="header-reference">About</a>
                    </li>
                    <c:choose>
                        <c:when test="${sessionScope.user.userRole.toString() == 'ADMIN'}">
                            <li>
                                <form class="text-end" method="get" action="${pageContext.request.contextPath}/main">
                                    <button type="submit" class="header-reference" name="command" value="admin">Administration</button>
                                </form>
                            </li>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </ul>

                <div class="nav ht-1 w-50 ml-auto justify-content-end">

                    <form class="w-25">
                        <input type="search" class="form-control form-control-dark" placeholder="Search..."
                               aria-label="Search">
                    </form>

                    <c:choose>
                        <c:when test="${sessionScope.user.userRole.toString() == 'USER' ||
                        sessionScope.user.userRole.toString() == 'ADMIN'}">
                            <div class="dropdown mx-4 flex-div-center">
                                <a class="dropbtn flex-item-center">
                                    <img src="${pageContext.request.contextPath}/image/default-user-icon.svg"
                                         width="30"
                                         height="30" alt="logo"
                                         class="rounded-circle">
                                </a>

                                <form method="get" action="${pageContext.request.contextPath}/main">
                                    <div class="dropdown-content">
                                        <button type="submit" class="dropdown-button" name="command"
                                                value="userProfile">
                                            My Profile
                                        </button>
                                        <button type="submit" class="dropdown-button" name="command" value="logout">
                                            Logout
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <form method="get"
                                  action="${pageContext.request.contextPath}/main">
                                <button type="submit" class="btn btn-outline-light mx-2"
                                        name="command" value="login">Login
                                </button>
                                <c:choose>
                                    <c:when test="${pageName == 'sign-up'}">
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-warning" name="command"
                                                value="registration">
                                            Sign-up
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </header>
</main>

</body>
</html>
