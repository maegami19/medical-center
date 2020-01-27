$(document).ready(function () {

    var validUsername = false;
    var validPassword = false;

    $("form").submit(function (event) {
        event.preventDefault();

        var username = $("#username").val();
        var password = $("#password").val();

        if (username == "" && password == "") {
            alert("Empty field username and password");
        } else if (username == "") {
            alert("Empty field username");
        } else if (password == "") {
            alert("Empty field password");
        } else {
            validUsername = true;
            validPassword = true;
        }
        if (validUsername == true && validPassword == true) {
            $("form").unbind('submit').submit();
        }
    });
});