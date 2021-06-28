$(function() {
    // Document is ready

    setViewFromStatus();

});

// 依狀態資料整理頁面
function setViewFromStatus() {
    console.log("setViewFromStatus()");

    $('.order-status').each(function(index, statusItem) {
        // 依狀態決定該訂單的操作按鈕是否綁定事件、是否可用
        refreshOperatorBtn(statusItem);
    });
}

// 更新按鈕狀態
function refreshOperatorBtn(statusItem) {
    console.log("refreshOperatorBtn()");

    var statusWord = $(statusItem).text();
    var $shipoutBtn = $(statusItem).next('.operatebtn').find('.status-shipout-btn');
    var $cancelBtn = $(statusItem).next('.operatebtn').find('.status-cancel-btn');
    switch (statusWord) {
        case "待出貨":
            // 綁定事件：點擊後AJAX送出該訂單更改狀態
            $shipoutBtn.on('click', changeStatus);
            $cancelBtn.on('click', changeStatus);
            // 不將按鈕功能失效
            break;
        case "待收貨":
            // 將"出貨"、"取消"按鈕失效
            // $shipoutBtn.off('click', changeStatus);
            // $cancelBtn.off('click', changeStatus);
            // $shipoutBtn.prop('disabled', true);
            // $cancelBtn.prop('disabled', true);
            // $shipoutBtn.fadeTo("fast", 0.6).css('cursor', 'not-allowed');
            // $cancelBtn.fadeTo("fast", 0.6).css('cursor', 'not-allowed');
            $(statusItem).next('.operatebtn').find('button').prop('disabled', true).fadeTo("fast", 0.6).css('cursor', 'not-allowed');
            break;
        case "取消":
            // 將"出貨"、"取消"按鈕失效
            // $shipoutBtn.off('click', changeStatus);
            $(statusItem).next('.operatebtn').find('button').prop('disabled', true).fadeTo("fast", 0.6).css('cursor', 'not-allowed');
            break;
    }
}

// 綁定事件：更新畫面和資料庫的訂單狀態
function changeStatus(e) {
    console.log("changeStatus()");

    var changingOrderId = $(this).siblings('.order-id').val();
    var statusBeforeUpdate = $(this).closest(".operatebtn").siblings('.order-status').text();
    switch ($(this).text()) {
        case "出貨":
            ajaxChangeStatusShipped(changingOrderId, statusBeforeUpdate);
            break;
        case "取消":
            ajaxChangeStatusCanceled(changingOrderId);
            break;
    }
}

// 取消：找到指定的訂單，將訂單ID、狀態，用ajax的DELETE方法向後端傳送
function ajaxChangeStatusCanceled(cancelingOrderId) {
    console.log("ajaxChangeStatusCanceled()");

    $.ajax({
        type: "DELETE",
        url: `/order/${cancelingOrderId}`,
        // data: JSON.stringify(jsonData),
        contentType: "application/json"
    }).done(function(newOrderDataMap) {
        // 將畫面上指定訂單的狀態欄位內容更新
        console.log("AJAX -> DONE");
        // console.log("---------------------------");
        // console.log("updatedOrderDataMap 如下:");
        // console.log(updatedOrderDataMap);
        // console.log("updatedOrderDataMap.orderId 如下:");
        // console.log(updatedOrderDataMap.orderId);
        // console.log("updatedOrderDataMap.status 如下:");
        // console.log(updatedOrderDataMap.status);
        // console.log("type of updatedOrderDataMap.status ==> " + typeof(updatedOrderDataMap.status));
        // console.log("---------------------------");

        refreshClickedOrderItem(newOrderDataMap.orderId, newOrderDataMap.status);
    }).fail(function() {
        ////////// 可以在畫面塞入錯誤訊息提示，可以請使用者重新整理再操作，或是其他補救措施
        // TODO
        console.log("AJAX -> FAIL");
    });
}

// 出貨(修改狀態)：找到指定的訂單，將訂單ID、狀態代號，用ajax的PUT方法向後端傳送
function ajaxChangeStatusShipped(updatingOrderId, statusBeforeUpdate) {
    console.log("ajaxChangeStatusShipped()");

    jsonData = { orderId: updatingOrderId, status: statusBeforeUpdate }

    $.ajax({
        type: "PUT",
        url: `/order/${updatingOrderId}`,
        data: JSON.stringify(jsonData),
        contentType: "application/json"
    }).done(function(updatedOrderDataMap) {
        // 將畫面上指定訂單的狀態欄位內容更新
        console.log("AJAX -> DONE");
        // console.log("---------------------------");
        // console.log("updatedOrderDataMap 如下:");
        // console.log(updatedOrderDataMap);
        // console.log("updatedOrderDataMap.orderId 如下:");
        // console.log(updatedOrderDataMap.orderId);
        // console.log("updatedOrderDataMap.status 如下:");
        // console.log(updatedOrderDataMap.status);
        // console.log("type of updatedOrderDataMap.status ==> " + typeof(updatedOrderDataMap.status));
        // console.log("---------------------------");

        refreshClickedOrderItem(updatedOrderDataMap.orderId, updatedOrderDataMap.status);
    }).fail(function() {
        ////////// 可以在畫面塞入錯誤訊息提示，可以請使用者重新整理再操作，或是其他補救措施
        // TODO
        console.log("AJAX -> FAIL");
    });
}

// 更新指定訂單的畫面
function refreshClickedOrderItem(itemId, newStatus) {
    console.log("refreshClickedOrderItem()");

    // 更新該訂單的狀態文字
    var $statusItem = $(`.order-id[value=${itemId}]`).closest(".operatebtn").siblings('.order-status')
    $statusItem.text(newStatus);
    // 更新該訂單操作按鈕的顯示
    var statusItem = $statusItem.get(0);
    refreshOperatorBtn(statusItem);
}