$(".delete-button").on("click", function() {
    var apple = $(this).attr('value');
    $.confirm({
        title: '請確認是否進行刪除',
        animation: 'zoom',
        closeAnimation: 'scale',
        content: '刪除後資料不可以復原喔!!!',
        buttons: {
            確認: function() {
                $.ajax({
                    type: "GET",
                    url: "/DeleteProduct",
                    async: false,
                    data: {
                        "productid": apple,

                    },
                    success: function(data) {
                        window.location.reload();
                    },
                    error: function(e) {
                        alert("OOPS!!!刪除錯誤囉!!!")
                    }
                })
            },
            我再想想: function() {

            }
        }
    });
});