$(function() {
    // Document is ready
    $('#status-shipout-btn').on('click', function() {
        updatingOrderId = $(this).siblings('#order-id').val();
        statusBeforeUpdate = $(this).closest("#operatebtn").prev('#order-status').text();
        console.log("原ID: " + updatingOrderId);
        console.log("原狀態: " + statusBeforeUpdate);
        updateStatusShipped(updatingOrderId, statusBeforeUpdate);

    });
});

function updateStatusShipped(updatingOrderId, statusBeforeUpdate) {
    jsonData = { orderId: updatingOrderId, status: statusBeforeUpdate }

    $.ajax({
        type: "PUT",
        url: "/orderStatus/update",
        data: JSON.stringify(jsonData),
        contentType: "application/json"
    }).done(function(updatedOrderId) {
        console.log("更新後返回ID: " + updatedOrderId);
        console.log("更新狀態改為: 待收貨");
    });

}