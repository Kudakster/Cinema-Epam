<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/pages/admin/admin.jsp"/>
<html lang="${sessionScope.get('locale')}">
<head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/add-movie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/imgPreview.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/add-movie.css" rel="stylesheet">
</head>
<body>
<form action="${pageContext.request.contextPath}/cinema/update-movie" method="post" id="basic-form"
      enctype="multipart/form-data">
    <div class="d-flex align-items-start justify-content-start margin-side-bar mt-3 position-relative">
        <div class="d-inline-block w-50 ms-1">
            <h3><fmt:message key="h.movie.update"/></h3>

            <div class="mb-3 mt-3" id="movie-name-form">
                <label for="movie-name" class="form-label"><fmt:message
                        key="label.movie.name"/></label>
                <input type="text" class="form-control" id="movie-name" placeholder="<fmt:message
                    key="placeholder.movie.enterName"/>" name="movie-name" maxlength="30"
                       value="${requestScope.movie.name}">
            </div>

            <div class="mb-3 mt-3" id="actors-form">
                <label for="actors" class="form-label"><fmt:message
                        key="label.movie.actors"/></label>
                <input type="text" class="form-control" id="actors" placeholder="<fmt:message
                    key="placeholder.movie.enterActors"/>" value="${requestScope.movie.actors}"
                name="actors" maxlength="30">
            </div>

            <div class="mb-3 mt-3" id="direction-form">
                <label for="direction" class="form-label"><fmt:message
                        key="label.movie.direction"/></label>
                <input type="text" class="form-control" id="direction" placeholder="<fmt:message
                    key="placeholder.movie.enterDirection"/>" value="${requestScope.movie.direction}"
                name="direction"
                maxlength="30">
            </div>

            <div class="mb-3 mt-3" id="genre-form">
                <label for="genre" class="form-label"><fmt:message
                        key="label.movie.genre"/></label>
                <input type="text" class="form-control" id="genre" placeholder="<fmt:message
                        key="placeholder.movie.enterGenre"/>" value="${requestScope.movie.genre}"
                name="genre" maxlength="30">
            </div>

            <div class="mb-3 mt-3" id="country-form">
                <label for="country" class="form-label"><fmt:message
                        key="label.movie.country"/></label>
                <input type="text" class="form-control" id="country" placeholder="<fmt:message
                        key="placeholder.movie.enterCountry"/>" value="${requestScope.movie.country}"
                name="country" maxlength="30">
            </div>

            <div class="mb-3 mt-3" id="trailer-url-form">
                <label for="trailer-url" class="form-label"><fmt:message
                        key="label.movie.trailerURL"/></label>
                <input type="text" class="form-control" id="trailer-url" placeholder="<fmt:message
                        key="placeholder.movie.enterTrailerURL"/>" value="${requestScope.movie.trailerURL}"
                name="trailer-url" maxlength="50">
            </div>

            <div class="mb-3 mt-3" id="release-date-form">
                <label for="release-date" class="form-label"><fmt:message
                        key="label.movie.release"/></label>
                <input type="text" class="form-control" id="release-date" placeholder="<fmt:message
                        key="placeholder.movie.enterRelease"/>" value="${requestScope.movie.releaseDate}"
                name="release-date" maxlength="10">
            </div>

            <div class="mb-3 mt-3" id="duration-min-form">
                <label for="duration-min" class="form-label"><fmt:message
                        key="label.movie.duration"/></label>
                <input type="text" class="form-control" id="duration-min" placeholder="<fmt:message
                        key="placeholder.movie.enterDuration"/>" value="${requestScope.movie.durationMin}"
                name="duration-min" maxlength="30">
            </div>

            <div class="description-absolute" id="description-form">
                <label for="description" class="form-label"><fmt:message key="label.movie.description"/></label>
                <textarea type="text" class="form-control" id="description"
                          placeholder="<fmt:message key="placeholder.movie.enterDescription"/>" value="${requestScope.movie.description}"
                name="description" maxlength="300"></textarea>
            </div>

            <input name="id" value="${requestScope.movie.id}" hidden>
            <button type="submit" class="btn btn-primary" id="submit-button" disabled><fmt:message
                    key="button.movie.update"/>
            </button>
        </div>

        <div class="d-inline-block container mt-5 ms-1 me-0 w-50 margin-header-double">
            <h5 class="mb-3 mt-0 text-center"><fmt:message key="h.movie.upload"/></h5>
            <div class="text-center">
                <div class="image-preview mb-3" id="frames">
                    <img src="${requestScope.movie.imgURL}" width="300px" height="400px">
                </div>
                <label for="image" class="btn btn-success-soft btn-block"><fmt:message
                        key="label.movie.upload"/></label>
                <input name="image-src" value="${requestScope.movie.imgURL}" hidden>
                <input type="file" class="mb-2" id="image" name="image" multiple hidden>
            </div>
        </div>
    </div>
</form>
</body>
</html>
