
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