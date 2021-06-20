
$(document).ready(function () {
    $('.Category-item').click(function () {
        $('.Category-item').removeClass('background-grey');
        $(this).addClass('background-grey');

        $('.Category-second-container').addClass('display-none');
        $(this).children().eq(2).removeClass('display-none')

        $('#category-first').text($(this).children().eq(0).text());
        switch ($(this).children().eq(0).text()) {
            case '女生衣著':
                switch ($('#category-second').text()) {
                    case 'T恤/T-Shirt':
                        switch ($('#category-third').text()) {
                            case '短袖T恤/T-Shirt':
                                break;
                            case '長袖T恤/T-Shirt':
                                break;
                            case '七分T恤/T-Shirt':
                                break;
                            case '大尺碼T恤/T-Shirt':
                                break;
                            case '其他T恤/T-Shirt':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '襯衫':
                        switch ($('#category-third').text()) {
                            case '短袖襯衫':
                                break;
                            case '長袖襯衫':
                                break;
                            case '七分襯衫':
                                break;
                            case '無袖襯衫':
                                break;
                            case '其他襯衫':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '洋裝':
                        switch ($('#category-third').text()) {
                            case '短袖洋裝':
                                break;
                            case '長袖洋裝':
                                break;
                            case '無袖洋裝':
                                break;
                            case '過膝長洋裝':
                                break;
                            case '其他洋裝':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '短褲':
                        switch ($('#category-third').text()) {
                            case '其他短褲':
                                break;
                            case '休閒短褲':
                                break;
                            case '牛仔短褲':
                                break;
                            case '內搭短褲':
                                break;
                            case '連身褲/吊帶褲':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '長褲':
                        switch ($('#category-third').text()) {
                            case '哈倫褲/西裝褲':
                                break;
                            case '七分褲':
                                break;
                            case '休閒長褲':
                                break;
                            case '寬褲':
                                break;
                            case '其他長褲':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '':
                        break;
                    default:
                        $('#category-second').text('');
                        $('#category-third').text('');
                        break;
                }
                break;
            case '男生衣著':
                switch ($('#category-second').text()) {
                    case 'T恤/T-Shirt':
                        switch ($('#category-third').text()) {
                            case '短袖T恤/T-Shirt':
                                break;
                            case '長袖T恤/T-Shirt':
                                break;
                            case '七分T恤/T-Shirt':
                                break;
                            case '大尺碼T恤/T-Shirt':
                                break;
                            case '其他T恤/T-Shirt':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '長褲':
                        switch ($('#category-third').text()) {
                            case '哈倫褲/西裝褲':
                                break;
                            case '七分褲':
                                break;
                            case '休閒長褲':
                                break;
                            case '牛仔長褲':
                                break;
                            case '其他長褲':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '外套':
                        switch ($('#category-third').text()) {
                            case '其他外套':
                                break;
                            case '棒球外套':
                                break;
                            case '羽絨外套':
                                break;
                            case '防風外套':
                                break;
                            case '西裝外套':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '短褲':
                        switch ($('#category-third').text()) {
                            case '其他短褲':
                                break;
                            case '休閒短褲':
                                break;
                            case '牛仔短褲':
                                break;
                            case '大尺碼短褲':
                                break;
                            case '海灘褲':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '運動服飾':
                        switch ($('#category-third').text()) {
                            case '其他運動服飾':
                                break;
                            case '運動上衣':
                                break;
                            case '運動褲':
                                break;
                            case '運動外套':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case '':
                        break;
                    default:
                        $('#category-second').text('');
                        $('#category-third').text('');
                        break;
                }
                break;
            case '書籍及文創相關商品':
                switch ($('#category-second').text()) {
                    case ' 教科參考書':
                        break;
                    case '雜誌期刊':
                        break;
                    case '文學小說':
                        break;
                    case '旅遊相關書籍':
                        break;
                    case '語言學習相關書籍':
                        break;
                    default:
                        $('#category-second').text('')
                        $('#category-third').text('');
                        break;
                }
                break;
            case '居家生活用品':
                switch ($('#category-second').text()) {
                    case "餐廚用品":
                        switch ($('#category-third').text()) {
                            case '刀具/砧板':
                                break;
                            case '餐具':
                                break;
                            case '保鮮盒/便當盒':
                                break;
                            case '烘焙用具':
                                break;
                            case '炒鍋/平底鍋':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case "收納用品":
                        switch ($('#category-third').text()) {
                            case '收納袋/壓縮袋':
                                break;
                            case '桌上收納':
                                break;
                            case '鞋盒/鞋架':
                                break;
                            case '層架/置物架':
                                break;
                            case '掛勾':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case "傢具/家具用品":
                        switch ($('#category-third').text()) {
                            case '沙發/沙發床':
                                break;
                            case '化妝台/化妝鏡':
                                break;
                            case '躺椅/折疊椅':
                                break;
                            case '餐桌/餐桌椅':
                                break;
                            case '茶几':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case "寢具用品":
                        switch ($('#category-third').text()) {
                            case '床包/床罩':
                                break;
                            case '床墊':
                                break;
                            case '睡袋':
                                break;
                            case '毯子':
                                break;
                            case '枕頭/枕頭罩':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case "五金用品":
                        switch ($('#category-third').text()) {
                            case '油漆顏料':
                                break;
                            case '鉗子/板手':
                                break;
                            case '工具梯':
                                break;
                            case '開關/電氣材料':
                                break;
                            case '螺絲起子':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    default:
                        $('#category-second').text('')
                        $('#category-third').text('')
                        break;
                }
                break;
            case '手機平板相關商品':
                switch ($('#category-second').text()) {
                    case "Apple空機":
                        switch ($('#category-third').text()) {
                            case 'IPHONE 12 系列':
                                break;
                            case 'IPHONE 11 系列':
                                break;
                            case 'IPHONE XR 系列':
                                break;
                            case 'IPHONE SE 系列':
                                break;
                            case 'IPHONE 其他系列':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case "Android空機":
                        switch ($('#category-third').text()) {
                            case 'Samsung系列':
                                break;
                            case 'Sony系列':
                                break;
                            case 'OPPO系列':
                                break;
                            case 'VIVO系列':
                                break;
                            case ' ASUS系列':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case "平板電腦":
                        switch ($('#category-third').text()) {
                            case 'IPad系列':
                                break;
                            case 'Android系列':
                                break;
                            case '其他系列':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case "耳機喇叭":
                        switch ($('#category-third').text()) {
                            case '有線耳機':
                                break;
                            case '藍牙耳機':
                                break;
                            case '有線喇叭':
                                break;
                            case '藍牙喇叭':
                                break;
                            case 'AirPods系列':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    case "行動電源":
                        switch ($('#category-third').text()) {
                            case '5000mAh以下':
                                break;
                            case '5000-10000mAh':
                                break;
                            case '10000mAh-20000mAh':
                                break;
                            case '20000mAh以上':
                                break;
                            case '其他':
                                break;
                            default:
                                $('#category-third').text('')
                                break;
                        }
                        break;
                    default:
                        $('#category-second').text('')
                        $('#category-third').text('')
                        break;
                }
                break;
            default:
                break;
        }
        if ($('#category-second').text() != "") {
            $("#span1").css('display', 'inline-block');
        } else {
            $("#span1").css('display', 'none');
        }
        if ($('#category-third').text() != "") {
            $("#span2").css('display', 'inline-block');
        } else {
            $("#span2").css('display', 'none');
        }

    })

    $('.Category-item-second').click(function () {
        $('.Category-item-second').removeClass('background-grey');
        $(this).addClass('background-grey');
        $('.Category-third-container').removeClass('display-block');
        $(this).children().eq(2).addClass('display-block');
        $('#category-second').text($(this).children().eq(0).text());


    })

    $('.Category-item-third').click(function () {
        $('.Category-item-third').removeClass('background-grey');
        $(this).addClass('background-grey');
        $("#span2").addClass('display-block')
        $('#category-third').text($(this).children().eq(0).text());
    })

    var change = $('.input-text').on('keyup', function (e) {
        console.log($(".input-text").val().length)
        e.preventDefault();
        $(".msg").css("display", "none");
        $(this).removeClass("border-red");

        $(".input-length").text($(".input-text").val().length)
    })

    $(".input-text").blur(function () {
        if ($(this).val() == null || $(this).val() == "") {
            // console.log("aaa")
            $(".msg").css("display", "block");
            $(this).addClass("border-red");
            //    console.log("bbb")
        } else if ($(this) != null || $(this).change == "") {
            $(".msg").css("display", "none");
            $(this).removeClass("border-red");
        } else {

        }
    })
})



