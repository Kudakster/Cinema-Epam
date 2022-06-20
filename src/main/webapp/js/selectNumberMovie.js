$(document).ready(function () {
    document.getElementById("select-page").value = getCookie("numberMovies");

    $("#select-page").on("change", function () {
        let value = document.getElementById("select-page").value;
        document.cookie = "numberMovies=" + value;
        document.location.reload();
    });

    let missedMovies = Number(getCookie("missedMovies"));
    let numberPages = Number(getCookie("numberMovies"));
    let numberMovies = Number(getCookie("numberAllMovies"));
    let previous = $("#previous");
    let next = $("#next");

    if (missedMovies === 0) {
        document.getElementById("previous").disabled = true;
        document.getElementById("next").disabled = false;
        previous.attr("class", "btn-second");
    } else {
        document.getElementById("previous").disabled = false;
        previous.attr("class", "btn-danger");
    }

    if (numberMovies <= missedMovies + numberPages) {
        document.getElementById("next").disabled = true;
        next.attr("class", "btn-second");
    } else {
        document.getElementById("next").disabled = false;
        next.attr("class", "btn-danger");
    }

    previous.on("click", function () {
        let result = missedMovies - numberPages;
        if (result < 0) {
            result = 0;
        }
        document.cookie = "missedMovies=" + result;
        document.location.reload();
    });

    next.on("click", function () {
        let result = missedMovies + numberPages;
        document.cookie = "missedMovies=" + result;
        document.location.reload();
    });
});