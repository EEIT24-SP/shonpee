$('.shopee-shop-filter-toggle-btn').on('click', function () {
    $(this).parent().find('.shopee-shop-filter-toggle').slideToggle(300);
    $(this).remove();
  })

  $('.total-price').parent().find('span').on('click', function () {
    $('.total-price-filter-toggle').toggle();
  })

$('.total-ranking').click(function () {
  $('.total-ranking').toggleClass('focus-on');
  
})
$('.total-latest').click(function () {
  $('.total-latest').toggleClass('focus-on');
  
})
$('.total-hots').click(function () { 
  $('.total-hots').toggleClass('focus-on');
})

$('.shop-search-right-product').mouseenter(function(){
  $(this).addClass('moveup')  
})
$('.shop-search-right-product').mouseleave(function(){
  $(this).removeClass('moveup')  
})
