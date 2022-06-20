<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/auditorium.css" rel="stylesheet">

</head>
<body>
<div class="d-block align-items-start justify-content-start margin-fixed position-relative">
    <div class="d-flex justify-content-center w-100 mb-4">
        <div class="d-flex w-auto">
            <div class="text-center">
                <form action="${pageContext.request.contextPath}/main" method="post">
                    <input class="input-name" type="text" name="name" id="name"
                           value="${requestScope.auditorium.auditoriumName}">
                    <input name="auditorium-id" id="auditorium-id" value="${requestScope.auditorium.auditoriumID}" hidden/>
                    <button type="submit" class="button-redact" id="button-redact" name="command" value="update-auditorium">
                        <img class="redact-icon" src="${pageContext.request.contextPath}/image/icon-check.svg"
                             width="15" height="15" alt="Confirm">
                    </button>
                </form>
            </div>
        </div>
    </div>
    <div class="d-flex justify-content-center">
        <table>
            <tr>
                <th>
                    <input type="number">
                </th>
                <th>
                    <button class="btn btn-primary">Add Row</button>
                </th>
            </tr>
            <tr>
                <th>
                    <input type="number">
                </th>
                <th>
                    <button class="btn btn-primary">Add Row</button>
                </th>
            </tr>
        </table>
    </div>
</div>

</body>
</html>
