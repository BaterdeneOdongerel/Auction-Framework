$( document ).ready(function() {
    $("#wrapper").toggleClass("toggled");
    $("#sidebar").click(function () {
        $("#wrapper").toggleClass("toggled");
    })
});