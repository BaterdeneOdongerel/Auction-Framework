/* ---------------------------------------------
 common scripts
 --------------------------------------------- */
(function ($) {
    $('.arrival-item').owlCarousel({
        navigation: true,
        pagination: false,
        items: 4,
        autoplay: false,
        navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],

    });
    $('.related-product').owlCarousel({
        navigation: true,
        pagination: false,
        items: 4,
        autoplay: false,
        navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
    });

    $('.logo-carousel').owlCarousel({
        //navigation: true,
        pagination: false,
        responsiveClass: true,
        //navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
        items: 4
    });
    /*---------------------
     countdown
     --------------------- */
    $('[data-countdown]').each(function () {
        var $this = $(this), finalDate = $(this).data('countdown');
        $this.countdown(finalDate, function (event) {
            $this.html(event.strftime('<span class="cdown days"><span class="time-count">%-D</span> <p>Days</p></span> <span class="cdown hour"><span class="time-count">%-H</span> <p>Hour</p></span> <span class="cdown minutes"><span class="time-count">%M</span> <p>Min</p></span> <span class="cdown second"> <span><span class="time-count">%S</span> <p>Sec</p></span>'));
        });
    });
    /*--------------------------
     scrollUp
     ---------------------------- */
    $.scrollUp({
        scrollText: '<i class="fa fa-angle-up"></i>',
        easingType: 'linear',
        scrollSpeed: 900,
        animation: 'fade'
    });

    // Search
    (function () {
        $(' a.top-search').on('click', function (e) {
            e.preventDefault();
            $('.show-search').slideToggle('fast');
            $(' a.top-search').toggleClass('sactive');
        });
    }());

})(jQuery);

