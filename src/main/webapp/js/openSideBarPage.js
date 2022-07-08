$(document).ready(function () {
    $("#content").load(getCookie("page"));

    openPage("movie", "page=movies", "http://localhost:8080/cinema/admin");
    openPage("auditorium", "page=auditorium", "http://localhost:8080/cinema/admin");
    openPage("a-movie", "page=a-movie", "http://localhost:8080/cinema/admin");
    openPage("u-schedule", "page=u-schedule", "http://localhost:8080/cinema/admin");
    openPage("statistic", "page=statistic", "http://localhost:8080/cinema/admin");
});

function openPage(idClick, cookie, URL) {
    $("#" + idClick).on("click", function () {
        document.cookie = cookie;
        location.href = URL;
    });
}
