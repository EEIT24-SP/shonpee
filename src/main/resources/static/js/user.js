/*下面是使用者主頁面標題*/
window.onload = function() {
    var profile_p_list_ctl = parseInt(window.sessionStorage.getItem('acc-profile'));
    var notice_p_list_ctl = parseInt(window.sessionStorage.getItem('acc-notice'));
    //設置列表載入初始值
    if (profile_p_list_ctl == 2) {
        profile_list.style.visibility = 'visible';
        profile_list.style.maxHeight = '150px';
        cnt_profile_list = 1;
    }
    if (notice_p_list_ctl == 2) {
        notice_list.style.visibility = 'visible';
        notice_list.style.maxHeight = '150px';
        cnt_notice_list = 1;
    }
    //設置主頁標題隱藏及顯示
};


//數量加減 

function item_plus() {
    var item_input_qty = document.getElementById('item-input-qty');
    var lastcount = document.getElementById('lastcount');
    var num = parseInt(lastcount.innerText);
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



/*控制者選單*/
var cnt_profile_list = 0;
var profile_list = document.getElementById('acc-profile');
var notice_list = document.getElementById('acc-notice');

function btn_list_profile() {
    var notice_p_list = parseInt(window.sessionStorage.getItem('acc-notice'));
    window.sessionStorage.setItem('acc-profile', '1');
    if (cnt_profile_list < 2) {
        profile_list.style.visibility = 'visible';
        profile_list.style.maxHeight = '150px';
        cnt_profile_list = cnt_profile_list + 1;
        window.sessionStorage.setItem('acc-profile', '2');
    }
    if (cnt_profile_list == 2) {
        profile_list.style.overflowY = 'hidden';
        profile_list.style.maxHeight = '0px';
        cnt_profile_list = 0;
        window.sessionStorage.setItem('acc-profile', '1');
    }
    //當點擊profile, sessionStorage 的value=2 時 隱藏 notice列表,且重置cnt,及 sessionStorage value
    if (notice_p_list == 2) {
        notice_list.style.overflowY = 'hidden';
        notice_list.style.maxHeight = '0px';
        cnt_notice_list = 0;
        window.sessionStorage.setItem('acc-notice', '1');
    }
}
var cnt_notice_list = 0;

function btn_list_notice() {
    var profile_p_list = parseInt(window.sessionStorage.getItem('acc-profile'));
    window.sessionStorage.setItem('acc-notice', '1');
    if (cnt_notice_list < 2) {
        notice_list.style.visibility = 'visible';
        notice_list.style.maxHeight = '150px';
        cnt_notice_list = cnt_notice_list + 1;
        window.sessionStorage.setItem('acc-notice', '2');
    }
    if (cnt_notice_list == 2) {
        notice_list.style.overflowY = 'hidden';
        notice_list.style.maxHeight = '0px';
        cnt_notice_list = 0;
        window.sessionStorage.setItem('acc-notice', '1');
    } //同上
    if (profile_p_list == 2) {
        profile_list.style.overflowY = 'hidden';
        profile_list.style.maxHeight = '0px';
        cnt_profile_list = 0;
        window.sessionStorage.setItem('acc-profile', '1');
    }
}

$('#item-input-qty').on('keyup', function(e) {
    this.value = this.value.replace(/[^0-9]/g, '')
    e.preventDefault();

});

$(document).on('click', function() {
    if ($('#item-input-qty').val() == "" || isNaN($('#item-input-qty').val())) {
        $('#item-input-qty').val(1);
    }
})



function view_img(myid) {
    document.getElementById('main-img').src = document.getElementById(`${myid.id}`).src;
}

function mousehover_img(myid) {
    document.getElementById('main-img').src = document.getElementById(`${myid.id}`).src;
}

function add() {
    document.getElementById('all_light').style.display = 'block';
    document.getElementById('content').style.display = 'block';
    document.getElementById('body').style.overflow = "hidden";
}

function add_1() {
    document.getElementById('all_light-1').style.display = 'block';
    document.getElementById('content-1').style.display = 'block';
    document.getElementById('body').style.overflow = "hidden";
}

function add_market() {
    document.getElementById('all_light-market').style.display = 'block';
    document.getElementById('content-market').style.display = 'block';
    document.getElementById('body').style.overflow = "hidden";
}

function add_address() {
    document.getElementById('all_light-address').style.display = 'block';
    document.getElementById('content-address').style.display = 'block';
    document.getElementById('body').style.overflow = "hidden";
}

var ccount = document.getElementById("ccount"); //显示商品总数量的标签节点对象 

/*下面是使用者主頁面標題*/
window.onload = function() {
    var profile_p_list_ctl = parseInt(window.sessionStorage.getItem('acc-profile'));
    var notice_p_list_ctl = parseInt(window.sessionStorage.getItem('acc-notice'));
    //設置列表載入初始值
    if (profile_p_list_ctl == 2) {
        profile_list.style.visibility = 'visible';
        profile_list.style.maxHeight = '150px';
        cnt_profile_list = 1;
    }
    if (notice_p_list_ctl == 2) {
        notice_list.style.visibility = 'visible';
        notice_list.style.maxHeight = '150px';
        cnt_notice_list = 1;
    }
    if (ccount.innerText != 0) {
        ccount.style.visibility = 'visible';
    } else if (ccount.innerText == 0) {
        ccount.style.visibility = 'hidden';
    }
    //設置主頁標題隱藏及顯示
};

/*控制者選單*/
var cnt_profile_list = 0;
var profile_list = document.getElementById('acc-profile');
var notice_list = document.getElementById('acc-notice');

function btn_list_profile() {
    var notice_p_list = parseInt(window.sessionStorage.getItem('acc-notice'));
    window.sessionStorage.setItem('acc-profile', '1');
    if (cnt_profile_list < 2) {
        profile_list.style.visibility = 'visible';
        profile_list.style.maxHeight = '150px';
        cnt_profile_list = cnt_profile_list + 1;
        window.sessionStorage.setItem('acc-profile', '2');
    }
    if (cnt_profile_list == 2) {
        profile_list.style.overflowY = 'hidden';
        profile_list.style.maxHeight = '0px';
        cnt_profile_list = 0;
        window.sessionStorage.setItem('acc-profile', '1');
    }
    //當點擊profile, sessionStorage 的value=2 時 隱藏 notice列表,且重置cnt,及 sessionStorage value
    if (notice_p_list == 2) {
        notice_list.style.overflowY = 'hidden';
        notice_list.style.maxHeight = '0px';
        cnt_notice_list = 0;
        window.sessionStorage.setItem('acc-notice', '1');
    }
}
var cnt_notice_list = 0;

function btn_list_notice() {
    var profile_p_list = parseInt(window.sessionStorage.getItem('acc-profile'));
    window.sessionStorage.setItem('acc-notice', '1');
    if (cnt_notice_list < 2) {
        notice_list.style.visibility = 'visible';
        notice_list.style.maxHeight = '150px';
        cnt_notice_list = cnt_notice_list + 1;
        window.sessionStorage.setItem('acc-notice', '2');
    }
    if (cnt_notice_list == 2) {
        notice_list.style.overflowY = 'hidden';
        notice_list.style.maxHeight = '0px';
        cnt_notice_list = 0;
        window.sessionStorage.setItem('acc-notice', '1');
    } //同上
    if (profile_p_list == 2) {
        profile_list.style.overflowY = 'hidden';
        profile_list.style.maxHeight = '0px';
        cnt_profile_list = 0;
        window.sessionStorage.setItem('acc-profile', '1');
    }
}

$("#FileTypeIcon").change(function() {
    var file = this.files[0];
    if (window.FileReader) {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onloadend = function(e) {
            $(".main-right-profile-right-container-img").attr("src", e.target.result);
        }
    }
});