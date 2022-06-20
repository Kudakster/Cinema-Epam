<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="${sessionScope.get('locale')}">
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/selectNumberMovie.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/movie.css" rel="stylesheet">

</head>
<body>
<div class="d-flex align-items-start justify-content-start position-relative">
    <div class="d-inline-block">
        <div class="flex align-items-center">
            <div class="float-start">
                <h3>Movies:</h3>
            </div>
            <div class="float-end">
                <label for="select-page">Movies Per Page</label>
                <select id="select-page" class="form-select-sm">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                </select>
            </div>
        </div>
        <div class="d-block align-items-center pt-3 rectangle-scroll h-auto flex-wrap d-flex" style="width: 1100px">
            <c:forEach items="${requestScope.movieList}" var="movie">
                <div class="rectangle ms-3 mb-3">
                    <div class="poster">
                        <img src="${pageContext.request.contextPath}${movie.imgURL}" width="200"
                             height="300">
                    </div>
                    <div class="image-overflow">
                        <form action="${pageContext.request.contextPath}/cinema/u-movie" method="get">
                            <input hidden name="u-movie-id" value="${movie.id}"/>
                            <div class="image-overflow-content top">
                                <button type="submit" class="bottom-redact">
                                    <img src="${pageContext.request.contextPath}/image/icon-pencil.svg" width="38"
                                         height="38">
                                </button>
                            </div>
                        </form>
                        <form action="${pageContext.request.contextPath}/cinema/delete-movie" method="get">
                            <input hidden name="d-movie-id" value="${movie.id}"/>
                            <div class="image-overflow-content bottom">
                                <button class="bottom-redact">
                                    <img class="button-trash"
                                         src="${pageContext.request.contextPath}/image/icon-trash.svg" width="50"
                                         height="50">
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="ms-3">
            <button class="btn-danger" id="previous" disabled>Previous</button>
            <button class="btn-danger" id="next">Next</button>
        </div>
    </div>
</div>
</body>
</html>
