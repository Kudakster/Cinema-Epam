<html>
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
<form action="${pageContext.request.contextPath}/main" method="post" id="basic-form"
      enctype="multipart/form-data">
    <div class="d-flex align-items-start justify-content-start margin-fixed position-relative">
        <div class="d-inline-block w-50 ms-1">
            <h3>Add New Movie</h3>

            <div class="mb-3 mt-3" id="movie-name-form">
                <label for="movie-name" class="form-label">Movie Name:</label>
                <input type="text" class="form-control" id="movie-name" placeholder="Enter Movie Name"
                       name="movie-name" maxlength="30">
            </div>

            <div class="mb-3 mt-3" id="actors-form">
                <label for="actors" class="form-label">Actors:</label>
                <input type="text" class="form-control" id="actors" placeholder="Enter Actors"
                       name="actors" maxlength="30">
            </div>

            <div class="mb-3 mt-3" id="direction-form">
                <label for="direction" class="form-label">Direction:</label>
                <input type="text" class="form-control" id="direction" placeholder="Enter Direction"
                       name="direction"
                       maxlength="30">
            </div>

            <div class="mb-3 mt-3" id="genre-form">
                <label for="genre" class="form-label">Genre:</label>
                <input type="text" class="form-control" id="genre" placeholder="Enter Genre"
                       name="genre" maxlength="30">
            </div>

            <div class="mb-3 mt-3" id="country-form">
                <label for="country" class="form-label">Country:</label>
                <input type="text" class="form-control" id="country" placeholder="Enter Country"
                       name="country" maxlength="30">
            </div>

            <div class="mb-3 mt-3" id="trailer-url-form">
                <label for="trailer-url" class="form-label">Trailer URL:</label>
                <input type="text" class="form-control" id="trailer-url" placeholder="Enter Trailer URL"
                       name="trailer-url" maxlength="50">
            </div>

            <div class="mb-3 mt-3" id="release-date-form">
                <label for="release-date" class="form-label">Release Date:</label>
                <input type="text" class="form-control" id="release-date" placeholder="Enter Release Date"
                       name="release-date" maxlength="10">
            </div>

            <div class="mb-3 mt-3" id="duration-min-form">
                <label for="duration-min" class="form-label">Duration Min:</label>
                <input type="text" class="form-control" id="duration-min" placeholder="Enter Duration Min"
                       name="duration-min" maxlength="30">
            </div>

            <div class="description-absolute" id="description-form">
                <label for="description" class="form-label">Description:</label>
                <textarea type="text" class="form-control" id="description"
                          placeholder="Enter Description"
                          name="description" maxlength="300"></textarea>
            </div>

            <button type="submit" class="btn btn-primary" id="submit-button" name="command"
                    value="add-movie" disabled>Add Movie
            </button>
        </div>

        <div class="d-inline-block container mt-5 ms-1 me-0 w-50 margin-header-double">
            <h5 class="mb-3 mt-0 text-center">Upload Movie Poster</h5>
            <div class="text-center">
                <!-- Image preview -->
                <div class="image-preview mb-3" id="frames"></div>
                <!-- Button -->
                <label for="image" class="btn btn-success-soft btn-block">Upload</label>
                <input type="file" class="mb-2" id="image" name="image" multiple hidden>
            </div>
        </div>
    </div>
</form>
</body>
</html>
