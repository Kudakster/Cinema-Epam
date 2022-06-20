$(document).ready(function () {
    $("#content").load("main?command=movies");

    $("#movie").on("click", function () {
        $("#content").load("main?command=movies");
    });

    $("#add-movie").on("click", function () {
        $("#content").load("main?command=movie");
    });

    $("#auditorium").on("click", function () {
        $("#content").load("main?command=auditorium");
    });
});

