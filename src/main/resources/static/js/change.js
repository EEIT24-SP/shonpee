
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

    var input_cnt = 0;
    var input_sel_list = document.getElementById("input-sel");
    var input_list = document.getElementById("input-list");
    function btn_input_year_list() {

        for (var i = 1900; i <= 2021; i++) {
            var input_sel_list_option = document.createElement("option");
            input_sel_list_option.innerHTML = i;

            input_sel_list.appendChild(input_sel_list_option);
        }
        if (input_cnt < 2) {
            input_sel_list.style.visibility = 'visible';
            input_cnt = input_cnt + 1;
            console.log(input_cnt);
        }
        if (input_cnt == 2) {
            input_sel_list.innerHTML = "";
            input_sel_list.style.visibility = 'hidden';
            input_cnt = 0;
        }
    }
    function input_year_sel() {
        input_list.value = input_sel_list.value;
        input_sel_list.innerHTML = "";
        input_sel_list.style.visibility = 'hidden'
        input_cnt = 0;
    }




    var input_month_cnt = 0;
    var input_month_sel_list = document.getElementById("input-month-sel");
    var input_month_list = document.getElementById("input-month-list");
    
    function btn_input_month_list() {

        for (var i = 1; i <= 12; i++) {
            var input_sel_list_option = document.createElement("option");
            input_sel_list_option.innerHTML = i;

            input_month_sel_list.appendChild(input_sel_list_option);
        }
        if (input_month_cnt < 2) {
            input_month_sel_list.style.visibility = 'visible';
            input_month_cnt = input_month_cnt + 1;
            console.log(input_month_cnt);
        }
        if (input_month_cnt == 2) {
            input_month_sel_list.innerHTML = "";
            input_month_sel_list.style.visibility = 'hidden';
            input_month_cnt = 0;
        }
    }
    function input_month_sel() {
        input_month_list.value = input_month_sel_list.value;
        input_month_sel_list.innerHTML = "";
        input_month_sel_list.style.visibility = 'hidden'
        input_month_cnt = 0;
    }


    var input_day_cnt = 0;
    var input_day_sel_list = document.getElementById("input-day-sel");
    var input_day_list = document.getElementById("input-day-list");
    function btn_input_day_list() {

        for (var i = 1; i <= 31; i++) {
            var input_sel_list_option = document.createElement("option");
            input_sel_list_option.innerHTML = i;

            input_day_sel_list.appendChild(input_sel_list_option);
        }
        if (input_day_cnt < 2) {
            input_day_sel_list.style.visibility = 'visible';
            input_day_cnt = input_day_cnt + 1;
            console.log(input_day_cnt);
        }
        if (input_day_cnt == 2) {
            input_day_sel_list.innerHTML = "";
            input_day_sel_list.style.visibility = 'hidden';
            input_day_cnt = 0;
        }
    }
    function input_day_sel() {
        input_day_list.value = input_day_sel_list.value;
        input_day_sel_list.innerHTML = "";
        input_day_sel_list.style.visibility = 'hidden'
        input_day_cnt = 0;
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

