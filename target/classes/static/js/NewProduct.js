function changepic(num) {
    document.querySelectorAll('.imgPreview')[num].style = 'border : grey solid 1px';
    var reads = new FileReader();
    f = document.getElementById(('file'+num)).files[0];
    reads.readAsDataURL(f);

    reads.onload = function(e) {
    
        document.getElementById('img'+num).src = this.result;
    };
    
    $('#img'+num).css("display", "block");

}

function deletePic(num){

    document.getElementById('img'+num).src='#';
    $('#img'+num).css("display", "none");
    $('#file'+num).val('');
    document.querySelectorAll('.imgPreview')[num].style = 'border : grey dashed 1px';
}

$('.prompt').mouseenter(function(){
    console.log(this)
    if($(this).find('.img-place').attr('src') != '#'){
        $(this).find('.img-delete-container').css('display' , 'block');
    }

})

$('.prompt').mouseleave(function(){
    $(this).find('.img-delete-container').css('display' , 'none');

})

$('.specification-plus-first').click(function(){
    $('.basic-information-specification-middle-first').append(`
    <div class="input-container-specification">
        <input type="text" name="" placeholder="請輸入選項" class="product-specification-first-type">
        <div class="delete-options"><img src="/pic/delete.png" alt=""></div>
    </div>
    `)
}) 
$('.specification-plus-second').click(function(){
    $('.basic-information-specification-middle-second').append(`
    <div class="input-container-specification">
        <input type="text" name="" placeholder="請輸入選項" class="product-specification-second-type">
        <div class="delete-options"><img src="/pic/delete.png" alt=""></div>
    </div>      
    `)
}) 

$(document).on('click','.delete-options',function(){
    $(this).parent().remove()
})

$(window).scroll(function () {

    if ($(this).scrollTop() > ($(document).height()-$(window).height()-1)) {
        console.log('apple')
        $('.submit-container').removeClass('fixbottom');
        $('.fixholder').addClass('display-none');
    }else{
        $('.submit-container').addClass('fixbottom');
        $('.fixholder').removeClass('display-none');

    }
});

document.getElementById("product-price").addEventListener("input",function(event){
    event.target.value = event.target.value.replace(/\-/g,""); 
});

$('#product-price').on('keyup',function(e){
        this.value=this.value.replace(/[^0-9]/g,'')
        e.preventDefault();
})
$('#product-count').on('keyup',function(e){
        this.value=this.value.replace(/[^0-9]/g,'')
        e.preventDefault();

})

$('#product-price').on('paste',function(e){
        e.preventDefault();
})
$('#product-count').on('paste',function(e){
        e.preventDefault();
})
