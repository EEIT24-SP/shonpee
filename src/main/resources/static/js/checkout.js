$(function() {

    $('.paymentmethod-choosingmethod-option').on('click', function() {
        $(this).siblings().addBack().removeClass('paymentmethod-option--selected');
        $(this).addClass('paymentmethod-option--selected');
        $('.checkout-payment-paymentmethod-methodcontent').children().removeClass('paymentmethod-content--unselected');

        if ($('.option-cod').hasClass('paymentmethod-option--selected')) {
            $('.paymentmethod-creditcard').addClass('paymentmethod-content--unselected');
        } else if ($('.option-creditcard').hasClass('paymentmethod-option--selected')) {
            $('.paymentmethod-cashondelivery').addClass('paymentmethod-content--unselected');
        }

    });



});