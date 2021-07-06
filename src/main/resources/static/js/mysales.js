$(function() {
    // Document is ready

    setViewFromStatus();

});

// 依狀態資料整理頁面
function setViewFromStatus() {
    $('.order-status').each(function(index, statusItem) {
        // 先將狀態欄位的數字，代換成有邏輯意義的中文文字
        replaceStatusNumToWord(statusItem);
        // 後依狀態文字，決定操作按鈕是否綁定事件、是否可用
        refreshOperatorBtn(statusItem);
    });
}

// 更新按鈕狀態
function refreshOperatorBtn(statusItem) {
    var statusWord = $(statusItem).text();
    var $shipoutBtn = $(statusItem).next('.operatebtn').find('.status-shipout-btn');
    var $cancelBtn = $(statusItem).next('.operatebtn').find('.status-cancel-btn');
    if (statusWord == "待出貨") {
        // 綁定事件：點擊後AJAX送出該訂單更改狀態
        $shipoutBtn.on('click', changeStatus);
        $cancelBtn.on('click', changeStatus);
        // 不將按鈕功能失效
    } else {
        // 將"出貨"、"取消"按鈕失效
        $(statusItem).next('.operatebtn').find('button').prop('disabled', true).fadeTo("fast", 0.6).css('cursor', 'not-allowed');
    }
}

// 綁定事件：更新畫面和資料庫的訂單狀態
function changeStatus(e) {
    var changingOrderId = $(this).siblings('.order-id').val();
    var statusNumBeforeChanging = changeStatusWordToNum($(this).closest(".operatebtn").siblings('.order-status').text());
    switch ($(this).text()) {
        case "出貨":
            ajaxChangeStatusShipped(changingOrderId, statusNumBeforeChanging);
            break;
        case "取消":
            ajaxChangeStatusCanceled(changingOrderId);
            break;
    }
}

// 出貨(修改狀態)：找到指定的訂單，將訂單ID、狀態代號，用ajax的PUT方法向後端傳送
function ajaxChangeStatusShipped(updatingOrderId, statusNumBeforeChanging) {
    jsonData = { orderId: updatingOrderId, status: statusNumBeforeChanging }

    $.ajax({
        type: "PUT",
        url: `/seller-order/${updatingOrderId}`,
        data: JSON.stringify(jsonData),
        contentType: "application/json"
    }).done(function(updatedOrderDataMap) {
        // 將畫面上指定訂單的狀態欄位內容更新
        refreshClickedOrderItem(updatedOrderDataMap.orderId, updatedOrderDataMap.status);
    }).fail(function() {
        ////////// 可以在畫面塞入錯誤訊息提示，可以請使用者重新整理再操作，或是其他補救措施
        // TODO
    });
}

// 取消：找到指定的訂單，將訂單ID、狀態，用ajax的DELETE方法向後端傳送
function ajaxChangeStatusCanceled(cancelingOrderId) {
    $.ajax({
        type: "DELETE",
        url: `/seller-order/${cancelingOrderId}`,
        // data: JSON.stringify(jsonData),
        contentType: "application/json"
    }).done(function(newOrderDataMap) {
        // 將畫面上指定訂單的狀態欄位內容更新
        refreshClickedOrderItem(newOrderDataMap.orderId, newOrderDataMap.status);
    }).fail(function() {
        ////////// 可以在畫面塞入錯誤訊息提示，可以請使用者重新整理再操作，或是其他補救措施
        // TODO
    });
}

// 更新指定訂單的畫面
function refreshClickedOrderItem(itemId, newStatusNum) {
    var $statusItem = $(`.order-id[value=${itemId}]`).closest(".operatebtn").siblings('.order-status');
    var statusItem = $statusItem.get(0);

    // 更新該訂單的狀態代號
    $statusItem.text(newStatusNum);

    // 更新該訂單的狀態內容文字，高亮2秒
    var newStatusWord = changeStatusNumToWord(newStatusNum);
    $statusItem.text(newStatusWord).css('color', 'rgb(206, 122, 89)').css('font-weight', 'bold');
    setTimeout(function() {
        $statusItem.css('color', 'black').css('font-weight', 'inherit');
    }, 600);

    // 更新該訂單操作按鈕的顯示
    refreshOperatorBtn(statusItem);
}


// 替換Thymeleaf帶入頁面的狀態數字，轉為邏輯意義中文文字
function replaceStatusNumToWord(statusItem) {
    var statusNum = parseInt($(statusItem).text());
    $(statusItem).text(changeStatusNumToWord(statusNum));
}

// 將訂單狀態數字轉為有邏輯意義的中文字，並傳回文字字串
function changeStatusNumToWord(statusNum) {
    switch (statusNum) {
        case 0:
            // 待付款
            return "待付款";
        case 1:
            // 待出貨
            return "待出貨";
        case 2:
            // 待收貨
            return "待收貨";
        case 3:
            // 完成
            return "完成";
        case 4:
            // 不成立
            return "不成立";
    }
}

function changeStatusWordToNum(statusWord) {
    switch (statusWord) {
        case "待付款":
            // 待付款
            return 0;
        case "待出貨":
            // 待出貨
            return 1;
        case "待收貨":
            // 待收貨
            return 2;
        case "完成":
            // 完成
            return 3;
        case "不成立":
            // 不成立
            return 4;
    }
}