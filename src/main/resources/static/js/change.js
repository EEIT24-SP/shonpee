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
var value1 =0;
$('.property-value-first').on("click",function(){
if(value1 ==0){
  	$(this).attr("name","typeValue1");
    $(this).addClass('property-select');
    value1= value1+1;
    console.log(value1);
}else if(value1>0){

 $(this).removeClass('property-select');
 $('.property-value-first').attr("name","");
     value1= 0;
}


  
})

var value2 =0;
$('.property-value-second').on("click",function(){
if(value2==0){
  	$(this).attr("name","typeValue2");
    $(this).addClass('property-select');
    value2= value2 + 1;
        console.log(value2);
}else if(value2 > 0){
 $(this).removeClass('property-select');
    $('.property-value-second').attr("name","");
        value2= 0;
    
   }
})