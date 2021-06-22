/*下面是使用者主頁面標題*/
window.onload = function () {
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

    item_input_qty.value = parseInt(item_input_qty.value) + 1;
    console.log(item_input_qty.value);
}
function item_minus() {
    var item_input_qty = document.getElementById('item-input-qty');

    if (item_input_qty.value == 1) {
        item_input_qty.value = item_input_qty.value;
    }
    else {
        item_input_qty.value = parseInt(item_input_qty.value) - 1;
    }
}



/*控制者選單*/
var cnt_profile_list = 0;
var profile_list = document.getElementById('acc-profile');
var notice_list = document.getElementById('acc-notice');
function btn_list_profile() {
    //     var profile_list = document.getElementById('acc-profile');
    // var notice_list= document.getElementById('acc-notice');
    var notice_p_list = parseInt(window.sessionStorage.getItem('acc-notice'));
    window.sessionStorage.setItem('acc-profile', '1');
    if (cnt_profile_list < 2) {
        profile_list.style.visibility = 'visible';
        profile_list.style.maxHeight = '150px';
        // asd.style.visibility = 'visible';
        // asd.style.maxHeight = '20px';
        cnt_profile_list = cnt_profile_list + 1;
        window.sessionStorage.setItem('acc-profile', '2');
    }
    if (cnt_profile_list == 2) {
        console.log(cnt_profile_list);
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
    // var notice_list = document.getElementById('acc-notice');    
    // var profile_list= document.getElementById('acc-profile');
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
var i;
var x = document.getElementsByClassName("main-right-list");
for (i = 1; i < x[0].children.length; i++) {
    x[0].children[i].style.display = "none";
}



function openTitle(titleName) {
    var i;
    var x = document.getElementsByClassName("main-right-list");
    // console.log(x[0].children[1]);
    for (i = 0; i < x[0].children.length; i++) {
        // x[i].style.display = "none";
        x[0].children[i].style.display = "none";
        // console.log(x);
    }
    document.getElementById(titleName).style.display = "block";
    if( document.getElementById(titleName)!=x[0].children[0]){
        document.getElementById('shop_list_serch').style.visibility="hidden";
        document.getElementById('shop_list_serch').style.position="absolute";


        console.log(x[0].children[0]);
    }else{
        document.getElementById('shop_list_serch').style.visibility="visible";
        document.getElementById('shop_list_serch').style.position="relative";

    }
}




function type_a_price() {
    var t = document.getElementById('item-price')
    t.innerHTML = '499';
}
function type_b_price() {
    var t = document.getElementById('item-price')
    t.innerHTML = '299';
}




function view_img(myid) {
    document.getElementById('main-img').src = document.getElementById(`${myid.id}`).src;
    // alert("id 為: " + myid.id);
    console.log(myid.id)

}
function mousehover_img(myid){
    document.getElementById('main-img').src = document.getElementById(`${myid.id}`).src;
    console.log(myid.id)

}


function add() {
    document.getElementById('all_light').style.display = 'block';
    document.getElementById('content').style.display = 'block';
    document.getElementById('body').style.overflow="hidden";


}
function add_1() {
    document.getElementById('all_light-1').style.display = 'block';
    document.getElementById('content-1').style.display = 'block';
    document.getElementById('body').style.overflow="hidden";

    
}

function add_market() {
    document.getElementById('all_light-market').style.display = 'block';
    document.getElementById('content-market').style.display = 'block';
    document.getElementById('body').style.overflow="hidden";

    
}
function add_address() {
    document.getElementById('all_light-address').style.display = 'block';
    document.getElementById('content-address').style.display = 'block';
    document.getElementById('body').style.overflow="hidden";

    
}