$(document).ready(function () {

    $("form").submit(function (event) {
        event.preventDefault();

        var diagnosis = $("#diagnosis").val();

        if (diagnosis == "") {
            alert("Fill diagnosis field!");
        } else {
            $("form").unbind('submit').submit();
        }
    });
});