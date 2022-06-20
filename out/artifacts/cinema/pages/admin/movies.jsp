<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/movie.css" rel="stylesheet">

</head>
<body>
<div class="d-flex align-items-start justify-content-start margin-fixed position-relative">
    <div class="d-inline-block w-100">
        <div class="text-start">
            <h3>Movies:</h3>
        </div>
        <div class="d-inline-block align-items-center py-3 rectangle-scroll w-100 flex-wrap d-flex">
            <div class="d-inline rounded-3 border-blue-dotted rectangle ms-3 flex-wrap d-flex">
                <img src="${pageContext.request.contextPath}${requestScope.movieList.get(0).imgURL}" width="200"
                     height="300">
            </div>
            <div class="d-inline rounded-3 border-blue-dotted rectangle ms-3 flex-wrap d-flex">
                <a class="button-add" href="#popup-first" id="popup-button">
                    <img src="${pageContext.request.contextPath}/image/circle-plus-red.svg"
                         class="circle-plus" aria-hidden="true" alt="Plus circle"/>
                </a>
            </div>

            <div id="popup-first" class="overlay">
                <div class="popup">
                    <a class="close" href="#">
                        <img src="${pageContext.request.contextPath}/image/close.svg" alt="Close">
                    </a>
                    <div class="content">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
