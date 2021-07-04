    var cot = 0;
    var obj = document.getElementById("view-img-0");
			 var imgSrc = obj.getAttribute("src");
			document.getElementById("main-img").src=imgSrc;	
		    document.getElementById("main-img-value").value=imgSrc;	
// Bearitem圖片控制
    function nex() {		  
		var photolength =parseInt(document.getElementById("photolength").value);
        if (cot <= photolength) {
            $('.item-container-left-b img').eq(cot).animate({ 'margin-left': '-89px' }, 100);
            cot++;
        }
    }
    function pre() {
        if (cot > 0) {
            cot--;
            $('.item-container-left-b img').eq(cot).animate({ 'margin-left': '0px' }, 100);
        }
    }
    function waithide() {
        var obj = document.getElementById("success-msg");
        obj.style.opacity = '1';
        window.setTimeout(
            function removethis() {
                obj.style.opacity = '0';
            }, 1000);
    }
$('.property-value-first').on("click",function(){
	
 $('.property-value-first').removeClass('property-select');
 $('.property-value-first').attr("name","");

  	$(this).attr("name","typeValue1");
    $(this).addClass('property-select');
  
})

$('.property-value-second').on("click",function(){

 $('.property-value-second').removeClass('property-select');
    $('.property-value-second').attr("name","");
  	$(this).attr("name","typeValue2");
    $(this).addClass('property-select');

    
  
})