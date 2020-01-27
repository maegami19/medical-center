$(document).ready(function () {

    $("form").submit(function (event) {
        event.preventDefault();

        var description = $("#description").val();

        if (description == "") {
            alert("Fill description field!");
        } else {
            $("form").unbind('submit').submit();
        }
    });
});