$(function() {
    // Document is ready

    // 購買數量欄位，頁面初始化，數量為1
    if ($('#item-input-qty').val() == "" || isNaN($('#item-input-qty').val())) {
        $('#item-input-qty').val(1);
    }

    // 購買數量欄位，過濾非數字以外的字元
    $('#item-input-qty').on('keyup', function(e) {
        this.value = this.value.replace(/[^0-9]/g, '')
        e.preventDefault();
    });

    // 先確認此頁是購物車頁(有單項商品金額)
    // 後綁定手動鍵入數字便計算單項商品金額和購物車總金額
    if ($('.product-total-price').length) {
        $('.numcount').on('change', refreshItemQtyAndTotals);
    }

    // 先確認此頁是購物車頁(有單項商品可勾選欄位)
    // 後綁定勾選後便計算單項商品金額和購物車總金額
    if ($('.cart-page-checkbox').length) {
        $('.cart-page-checkbox').on('click', reCalcTotal);
    }


});



// 更新單項商品數量，更新頁面各金額
function refreshItemQtyAndTotals() {
    // 手動鍵入的數量
    var keyedQty = parseInt($(this).val());
    // 單項商品總金額的HTML元素
    var subtotalDOM = $(this).parents('.product-quanftity').siblings('.product-total-price-box').children('.product-total-price').get(0);
    // 若鍵入的數量小於1，則自動換成1
    if (keyedQty < 1) {
        $(this).val(1);
    }
    // 單項商品總計更新
    reCalcSubtotal(subtotalDOM);
    // 更新結帳總金額
    reCalcTotal();
}

// 重新計算單項商品金額
function reCalcSubtotal(subtotalDOM) {
    var qty = parseInt($(subtotalDOM).parents('.product-total-price-box').siblings('.product-quanftity').find('.numcount').val());
    var itemPrice = parseInt($(subtotalDOM).parents('.product-total-price-box').siblings('.product-unit-price').find('.unit-price').val());
    $(subtotalDOM).prop('value', qty * itemPrice);
}

// 重新計算有勾選的商品的總金額
function reCalcTotal() {
    var newTotal = 0;
    $('.cart-page-product-allitems').each(function(i, item) {
        var isItemChecked = $(item).find('.cart-page-checkbox').prop("checked");
        if (isItemChecked == true) {
            var eachSubtotal = parseInt($(item).find('.product-total-price').val());
            newTotal = newTotal + eachSubtotal;
            $('#cart-page-flex-fixed-footer-thredroll-settleaccounts-total-price').text(newTotal);
        }
    });
}

// 購買數量欄位，數量加減
function item_plus() {
    var item_input_qty = document.getElementById('item-input-qty');
    var lastcount = document.getElementById('lastcount');
    var num = parseInt(lastcount.innerText)
    if (item_input_qty.value < num) {
        item_input_qty.value = parseInt(item_input_qty.value) + 1;
    }
}

function item_minus() {
    var item_input_qty = document.getElementById('item-input-qty');
    if (item_input_qty.value == 1 || item_input_qty.value == '' || isNaN(item_input_qty.value)) {
        item_input_qty.value = 1;
    } else {
        item_input_qty.value = parseInt(item_input_qty.value) - 1;
    }
}