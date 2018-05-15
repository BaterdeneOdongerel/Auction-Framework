function initial() {


    $("#signup").click(function () {
        if (!checkValidation()) {
            return false;
        }
    });
}

function checkValidation() {
    var username = $("#username");
    var fname = $("#firstname");
    var lname = $("#lastname");
    var pass = $("#password");
    var rpass = $("#rpassword");
    var email = $("#email");
    $("form div").removeClass("has-error");

    if ( username.val().toString().length == 0) {
        username.parent().parent().addClass("has-error");
        return false;
    }
    if ( fname.val().toString().length == 0) {
        fname.parent().parent().addClass("has-error");
        return false;
    }

    if ( pass.val().toString().length == 0 || pass.val() != rpass.val() ) {
        pass.parent().parent().addClass("has-error");
        rpass.parent().parent().addClass("has-error");
        return false;
    }

    if ( email.val().toString().length == 0) {
        email.parent().parent().addClass("has-error");
        return false;
    }
    return true;
}


$( document ).ready(function() {
    initial();
});