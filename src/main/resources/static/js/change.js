var cot = 0;//設置一個計數器，初始值為0；作用是用來監聽點擊切換的時候哪一個圖片應該隱藏或者顯示
    function nex() {
        if (cot <= 3) {
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
        //获取文本输入框中的内容
        for (var i = 1900; i <= 2021; i++) {
            //获取到Ul列表
            //创建元素Li
            var input_sel_list_option = document.createElement("option");
            //向li中添加内容
            input_sel_list_option.innerHTML = i;
            //向ul追加元素li
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
        //获取文本输入框中的内容
        for (var i = 1; i <= 12; i++) {
            //获取到Ul列表
            //创建元素Li
            var input_sel_list_option = document.createElement("option");
            //向li中添加内容
            input_sel_list_option.innerHTML = i;
            //向ul追加元素li
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
        //获取文本输入框中的内容
        for (var i = 1; i <= 31; i++) {
            //获取到Ul列表
            //创建元素Li
            var input_sel_list_option = document.createElement("option");
            //向li中添加内容
            input_sel_list_option.innerHTML = i;
            //向ul追加元素li
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





    // var input_month_cnt = 0;
    // var input_month_sel_list = document.getElementById("input-month-sel");
    // var input_month_list = document.getElementById("input-month-list");
    // var arr =[1,2,3];

    // function btn_input_month_list() {
    //     //获取文本输入框中的内容
    //     for (var i = 0; i < arr.length; i++) {
    //         //获取到Ul列表
    //         //创建元素Li
    //         var input_sel_list_option = document.createElement("option");
    //         //向li中添加内容
    //         input_sel_list_option.innerHTML =arr[i];
    //         //向ul追加元素li
    //         input_month_sel_list.appendChild(input_sel_list_option);
    //     }
    //     if (input_month_cnt < 2) {
    //         input_month_sel_list.style.visibility = 'visible';
    //         input_month_cnt = input_month_cnt + 1;
    //         console.log(input_month_cnt);
    //     }
    //     if (input_month_cnt == 2) {
    //         input_month_sel_list.innerHTML = "";
    //         input_month_sel_list.style.visibility = 'hidden';
    //         input_month_cnt = 0;
    //     }
    // }