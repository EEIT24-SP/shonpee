$(function() {

    // document ready

    // "加入購物車"按鈕，綁定"成功加入購物車"事件
    $('.item-cart-btn-join').on('click', addCartSuccess);




});

// 顯示"成功加入購物車"
function addCartSuccess() {
    $('#success-msg').css('opacity', '1').css('z-index', '10');
}