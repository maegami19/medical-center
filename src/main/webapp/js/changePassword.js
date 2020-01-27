$(document).ready(function () {

    var validUsername = false;

    $("form").submit(function (event) {
        event.preventDefault();

        var currentpassword = $("#currentpass").val();
        var newpass = $("#newpass").val();
        var repeatnewpass = $("#repeatnewpass").val();

        if (currentpassword == "" || newpass == "" || repeatnewpass == "") {
            alert("Fill all fields!");
        } else if (newpass.length < 8) {
            alert("Password can be 8+ symbols!");
        } else if (repeatnewpass != newpass) {
            alert("Passwords do not match!");
        } else if (newpass == currentpassword) {
            alert("Current password and new password must be different!");
        } else {
            $("form").unbind('submit').submit();
        }
    });
});