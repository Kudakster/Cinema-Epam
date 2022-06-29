<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://code.jquery.com/ui/1.12.0-rc.2/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/js/minDate.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/updateSchedule.css" rel="stylesheet">
</head>
<body>
<div class="d-flex align-items-start justify-content-start position-relative">
    <div class="d-inline-block w-100">
        <h3><fmt:message key="h.addScreening"/></h3>
            <div class="ms-3 mb-3" id="screening-form">
                <form action="${pageContext.request.contextPath}/cinema/add-screening"
                      method="post">

                    <div class="mb-3 mt-3 wrapper">
                        <label for="movie-name"><fmt:message key="label.movie.name"/>
                            <input class="form-control" name="movie-name" type="text">
                        </label>
                    </div>

                    <div class="mb-3 mt-3">
                        <label for="date" class="form-label"><fmt:message key="label.date"/>
                            <input type="date" id="date-form" class="form-control w-100" name="date"
                                   min=""/>
                        </label>
                    </div>

                    <div class="mb-3 mt-3">
                        <label class="form-label"><fmt:message key="label.startTime"/>
                            <input type="time" class="form-control w-100" name="startTime"
                                   min="09:00:00" max="22:00:00"/>
                        </label>
                    </div>

                    <div class="mb-3 mt-3">
                        <label class="form-label"><fmt:message key="label.endTime"/>
                            <input type="time" class="form-control w-100" name="endTime"
                                   min="09:00:00" max="00:00:00"/>
                        </label>
                    </div>

                    <button type="submit" class="btn btn-primary mb-2" style="width: 72px"><fmt:message key="button.save"/>
                    </button>
                </form>
            </div>
            <c:forEach items="${requestScope.screeningMap}" var="item">
                <h3>${item.key}</h3>
                <div class="d-block align-items-center pt-3 w-100 h-auto flex-wrap d-flex" name="day-block"
                     id="day-block">
                    <c:forEach items="${item.value}" var="screening">
                        <div class="ms-3 mb-3" id="screening-form">
                                <input id="screeningID" class="form-control" name="screeningID"
                                       value="${screening.screeningID}" hidden/>

                                <div class="mb-3 mt-3 wrapper">
                                    <div class="search-input">
                                        <a href="" target="_blank" hidden></a>
                                        <label for="movie-name"><fmt:message key="label.movie.name"/></label>
                                        <input id="movie-name" class="form-control" name="movie-name" type="text"
                                               value="${requestScope.movieMap.get(screening.movieID)}">
                                        <div class="autocom-box"></div>
                                    </div>
                                </div>

                                <div class="mb-3 mt-3">
                                    <label for="date" class="form-label"><fmt:message key="label.date"/>
                                        <input type="date" id="date" class="form-control w-100" name="date"
                                               min="${screening.date}" value="${screening.date}"/>
                                    </label>
                                </div>

                                <div class="mb-3 mt-3">
                                    <label for="startTime" class="form-label"><fmt:message key="label.startTime"/>
                                        <input type="time" id="startTime" class="form-control w-100" name="startTime"
                                               min="09:00:00" max="22:00:00" value="${screening.startTime}"/>
                                    </label>
                                </div>

                                <div class="mb-3 mt-3">
                                    <label for="endTime" class="form-label"><fmt:message key="label.endTime"/>
                                        <input type="time" id="endTime" class="form-control w-100" name="endTime"
                                               min="09:00:00" max="00:00:00" value="${screening.endTime}"/>
                                    </label>
                                </div>

                            <form action="${pageContext.request.contextPath}/cinema/delete-screening"
                                  id="delete-form">
                                <input name="screening-id" value="${screening.screeningID}" hidden>
                                <button type="submit" class="btn btn-danger" id="delete-screening" style="width: 72px">
                                    <fmt:message key="button.delete"/>
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
