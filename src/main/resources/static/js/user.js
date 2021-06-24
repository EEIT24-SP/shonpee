
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

$('#item-input-qty').on('keyup',function(e){
    this.value=this.value.replace(/[^0-9]/g,'')
    e.preventDefault();

})




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

