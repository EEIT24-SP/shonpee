$(document).ready(function() {

    if ($('#second-span').text() != "") {
        $("#span1").css('display', 'inline-block');
    } else {
        $("#span1").css('display', 'none');
    }
    if ($('#third-span').text() != "") {
        $("#span2").css('display', 'inline-block');
    } else {
        $("#span2").css('display', 'none');
    }


})

$('.prompt').mouseenter(function() {
    if ($(this).find('.img-place').attr('src') != null) {
        $(this).find('.img-delete-container').css('display', 'block');
    }

})

$('.prompt').mouseleave(function() {
    $(this).find('.img-delete-container').css('display', 'none');

})


$('.specification-plus-first').click(function() {
    $('.basic-information-specification-middle-first').append(`
	    <div class="input-container-specification">
	        <input type="text" name="propertyValue" placeholder="請輸入選項" class="product-specification-first-type">
	        <div class="delete-options"><img src="/pic/delete.png" alt=""></div>
	    </div>
	    `)
})
$('.specification-plus-second').click(function() {
    $('.basic-information-specification-middle-second').append(`
	    <div class="input-container-specification">
	        <input type="text" name="propertySecondValue" placeholder="請輸入選項" class="product-specification-second-type">
	        <div class="delete-options"><img src="/pic/delete.png" alt=""></div>
	    </div>      
	    `)
})

$(document).on('click', '.delete-options', function() {
    $(this).parent().remove()
})

$(window).scroll(function() {

    if ($(this).scrollTop() > ($(document).height() - $(window).height() - 1)) {
        $('.submit-container').removeClass('fixbottom');
        $('.fixholder').addClass('display-none');
    } else {
        $('.submit-container').addClass('fixbottom');
        $('.fixholder').removeClass('display-none');

    }
});

$('#product-price').on('keyup', function(e) {
    this.value = this.value.replace(/[^0-9]/g, '')
    e.preventDefault();
})
$('#product-count').on('keyup', function(e) {
    this.value = this.value.replace(/[^0-9]/g, '')
    e.preventDefault();

})

$('#product-price').on('paste', function(e) {
    e.preventDefault();
})
$('#product-count').on('paste', function(e) {
    e.preventDefault();
})


$('#product-name').on('keyup', function(e) {
    e.preventDefault();
    $(".msg").css("display", "none");
    $(this).removeClass("border-red");

})

$("#product-name").blur(function() {
    if ($(this).val() == null || $(this).val() == "") {
        $(".msg").css("display", "block");
        $(this).addClass("border-red");
    } else if ($(this).change != null || $(this).change == "") {
        $(".msg").css("display", "none");
        $(this).removeClass("border-red");
    }
})



$("#submitbtn").on('click', function() {
    var allsrc = '';
    var imgs = '';
    $('.img-place').each(function() {
        if (typeof($(this).attr('src')) != "undefined") {
            allsrc = $(this).attr('src') + allsrc;
        }
    });
    $('.img-place').each(function() {
        if (typeof($(this).attr('src')) != "undefined") {
            if ($(this).attr('src').length < 500) {
                imgs = imgs + "," + $(this).attr('src');
            } else {
                imgs = imgs + "," + "newpic";
            }
        }
    });
    var photoarr = imgs.split(",").filter(item => item);
    if ($("#product-name").val() == "") {
        alert("你尚未填商品名稱");
        return false;
    } else if (allsrc.length < 10) {
        alert("你尚未放入圖片");
        return false;
    } else if ($("#product-description").val() == "") {
        alert("你尚未填商品描述");
        return false;
    } else if ($("#product-price").val() == "") {
        alert("你尚未填寫商品價格");
        return false;
    } else if ($("#product-count").val() == "") {
        alert("你尚未填寫商品數量");
        return false;
    } else {
        $('#oldPhoto').attr('value', imgs.split(",").filter(item => item));
        $('#submitbtn').attr('value', $('#first-span').text() + "," + $('#second-span').text() + "," + $('#third-span').text())

    }
})


function changepic(num) {
    document.querySelectorAll('.imgPreview')[num].style = 'border : grey solid 1px';
    var reads = new FileReader();
    f = document.getElementById(('file' + num)).files[0];
    reads.readAsDataURL(f);

    reads.onload = function(e) {

        document.getElementById('img' + num).src = this.result;
    };

    $('#img' + num).css("display", "block");

}

function deletePic(num) {


    document.getElementById('img' + num).src = '';
    $('#img' + num).css("display", "none");
    $('#file' + num).val('');
    document.querySelectorAll('.imgPreview')[num].style = 'border : grey dashed 1px';
}


function submitMyFrom(path) {
    $('form1').action = path;
    $('form1').submit();
}