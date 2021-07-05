$('.cart-page-checkboxTotal').on('click',function(){
		if($('.cart-page-checkbox').prop('checked')==false){
		$('.cart-page-checkbox').prop('checked','checked');
		}else{
			$('.cart-page-checkbox').prop('checked','');
		}
	});
	
	$(function(){
		$('.cart-page-checkbox').prop("checked","");
		$('.product-action-delet').prop('disabled','disabled');
	});

	$('.cart-page-checkbox').on('click',function(){
        var CKBXChecked = $(this).prop("checked");
    	console.log($('.cart-page-checkbox').length);
        if(CKBXChecked == true){
		var Cid =  $(this).parents().children('.Cart_id').val();
		$(this).parents().children().children('.product-action-delet').removeAttr('disabled')
		$(this).prop('value',Cid);
		$(this).parents().children('.product-total-price-box').children('.product-total-price').prop('name','totalPrice');
		$(this).parents().children('.product-quanftity').children('div').children('.numcount').prop('name','Quantity');
		$(this).parents().children('.product-size').children('div').children('.typevalue1').prop('name','typeValue1');
		$(this).parents().children('.product-size').children('div').children('.typevalue2').prop('name','typeValue2');
        }else if(CKBXChecked == false){
        	$(this).parents().children().children('.product-action-delet').prop('disabled','disabled')
    		$(this).removeAttr('value');
    		$(this).parents().children('.product-total-price-box').children('.product-total-price').removeAttr('name');
    		$(this).parents().children('.product-quanftity').children('div').children('.numcount').removeAttr('name');
    		$(this).parents().children('.product-size').children('div').children('.typevalue1').removeAttr('name');
    		$(this).parents().children('.product-size').children('div').children('.typevalue2').removeAttr('name');
        }
		
	
	});
	
	$('.product-action-delet').on('click',function(){		
			var Cid =  $(this).parents().children('.Cart_id').val();
			$(this).prop('value',Cid);
	});

	$('.plus').on('click', function () {
			var temp = $(this).parent().children('.numcount').prop('value');
			var total = $(this).parents().children('.product-total-price-box').children('.product-total-price').val();
			$(this).parents().children('.numcount').prop('value', parseInt(temp) + 1);
			var price = $(this).parents().children('.product-unit-price').children('.unit-price').val();
			$(this).parents().children('.product-total-price-box').children('.product-total-price').prop('value', (parseInt(total) + parseInt(price)));
            var isChecked = $(this).parents().children('.cart-page-checkbox').prop("checked");
            if(isChecked == true){
            	var plustotal =parseInt($(".totals").text());
    			var Ptotal = plustotal+parseInt(price);
    			$(".totals").text(Ptotal)
            }
			
			
		});
		$('.minus').on('click', function () {
			var temp = $(this).parent().children('.numcount').val();
			var total = $(this).parents().children('.product-total-price-box').children('.product-total-price').val();
			var price = $(this).parents().children('.product-unit-price').children('.unit-price').val();
			if (temp > 1) {
				$(this).parents().children('.numcount').prop('value', parseInt(temp) - 1);
				$(this).parents().children('.product-total-price-box').children('.product-total-price').prop('value', (parseInt(total) - parseInt(price)));
				  var isChecked = $(this).parents().children('.cart-page-checkbox').prop("checked");
		            if(isChecked == true){
				var minustotal =parseInt($(".totals").text());
				var Mtotal = minustotal-parseInt(price);
				$(".totals").text(Mtotal)
				}
			}
		});
		
		$('.numcount').on('change',function () {
			var QT =$(this).val();
			var price = $(this).parents().children('.product-unit-price').children('.unit-price').val();
			$(this).parents().children('.product-total-price-box').children('.product-total-price').prop('value', (parseInt(QT) * parseInt(price)));
          var total=0
          $(".cart-page-checkbox").each(function () {
              var isChecked = $(this).prop("checked");
              // 如果多選框被選中
              if(isChecked == true) {
            	  var id = parseInt( $(this).parents().children('.product-total-price-box').children('.product-total-price').val());
                  //4. 累加計算
                  total += Number(id); 
              }
          })
              $(".totals").text(total);
		});
		
		
		
		
		 $(function() {
        // 載入完頁面時,計算總計
        showTotal();
        console.log( showTotal());
        $('.cart-page-checkbox').on('click', function () {
            // 選擇多選框後,再重新計算
            showTotal();
        });

    });

    // 計算總計
    function showTotal() {

        var  total = 0;
        var  number = 0;
        // 1. 獲取所有的被勾選的條目複選框！迴圈遍歷
        $(".cart-page-checkbox").each(function () {
            var isChecked = $(this).prop("checked");
            // 如果多選框被選中
            if(isChecked == true) {
                // 2. 獲取複選框的值，即其他元素的字首
                var id = parseInt( $(this).parents().children('.product-total-price-box').children('.product-total-price').val());
                // alert("id" + id);
                //3. 再通過字首找到小計元素，獲取其文字
                var text = $("#" + id + "Subtotal").text();
                // alert(text);
                //4. 累加計算
                total += Number(id);
                number += 1;
                // alert(total);
            }
            if(number!=0){
            	$('#cart-checkout-type').removeAttr('disabled');}
            else{$('#cart-checkout-type').prop('disabled','disabled');}
        });
        // 5. 把總計顯示在總計元素上
        $(".txts").text(number);//toFixed(2)函式的作用是把total保留2位
        $(".totals").text(total);
    }