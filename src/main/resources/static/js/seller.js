$('.seller-followbtn').click(function(){
    $('.seller-followbtn').toggleClass('following');
    $('.seller-followbtn').find('img').toggle();

    if($('.seller-followbtn').text()==('關注')){
        $('.seller-followbtn').append('中');
    }else{
        $('.seller-followbtn').html('<img src="/pic/pluswhite.png" alt="">關注');
    }

})
