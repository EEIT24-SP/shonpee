function change_login() {
    document.getElementById("login_mid_right_login").style.zIndex = 1;
    document.getElementById("login_mid_right_registered").style.zIndex = 0;
}

function change_registered() {
    document.getElementById("login_mid_right_login").style.zIndex = 0;
    document.getElementById("login_mid_right_registered").style.zIndex = 1;
}

//(輸入、註冊密碼)&(顯示or隱藏密碼)
// var login_password = [];//真正的密碼
// var login_arraylocation = 0;//游標位置
// function login_hidden_password(e) {
//     if (document.getElementById('login_mid_right_APE_eye_loginclose').style.visibility == 'visible') {
//         if (e.key.length == 1) {
//             document.getElementById('password_input').value = ('*'.repeat(login_password.length));
//             login_password.splice(login_arraylocation, 0, e.key);
//             // console.log('login_password=' + login_password);
//             login_arraylocation++;
//             console.log(login_arraylocation)
//         } else if (e.key == 'ArrowLeft' && (login_arraylocation > 0)) {
//             login_arraylocation--;
//             console.log(login_arraylocation)
//         } else if (e.key == 'ArrowRight' && (login_arraylocation < login_password.length)) {
//             login_arraylocation++;
//             console.log(login_arraylocation)
//         } else if (e.key == 'ArrowUp') {
//             login_arraylocation = 0;
//         } else if (e.key == 'ArrowDown') {
//             login_arraylocation = login_password.length;
//         } else if (e.key == "Backspace") {
//             if ((login_arraylocation > 0) && (login_arraylocation < login_password.length + 1)) {
//                 login_password.splice(login_arraylocation - 1, 1);
//                 login_arraylocation--;
//                 console.log('login_password=' + login_password)
//             }
//         }
        // console.log(e.key)

        // } else {
        //     document.getElementById('password_input').onkeyup=(function(){
        //     console.log( Array.from(document.getElementById('password_input').value));
        //     login_password=Array.from(document.getElementById('password_input').value);
        //     })
//     }
// }



// var registered_password = [];
// var registered_arraylocation = 0;
// function registered_hidden_password(e) {
        //  if (document.getElementById('login_mid_right_APE_eye_registeredclose').style.visibility == 'visible') {
    //         if (e.key.length == 1) {
    //             // document.getElementById('password_input_registered').value = ('*'.repeat(registered_password.length));
    //             (e.key.value).rep
    //             registered_password.splice(registered_arraylocation, 0, e.key);
    //             console.log('registered_password=' + registered_password);
    //             registered_arraylocation++;
    //         } else if (e.key == 'ArrowLeft' && (registered_arraylocation > 0)) {
    //             registered_arraylocation--;
    //             console.log(registered_arraylocation)
    //         } else if (e.key == 'ArrowRight' && (registered_arraylocation < login_password.length)) {
    //             registered_arraylocation++;
    //             console.log(registered_arraylocation)
    //         } else if (e.key == 'ArrowUp') {
    //             registered_arraylocation = 0;
    //         } else if (e.key == 'ArrowDown') {
    //             registered_arraylocation = registered_password.length;
    //         } else if (e.key == "Backspace") {
    //             if ((registered_arraylocation > 0) && (registered_arraylocation < registered_password.length + 1)) {
    //                 registered_password.splice(registered_arraylocation - 1, 1);
    //                 registered_arraylocation--;
    //                 console.log('registered_password=' + registered_password)
    //             }
    //         }
            // } else {
            //     document.getElementById('password_input_registered').onkeyup=(function(){
            //     //    console.log( Array.from(document.getElementById('password_input').value));
            //     registered_password=Array.from(document.getElementById('password_input_registered').value);
            //     })
    //     }
    // }



//密碼眼睛切換
function login_eyes_visible(e) {
    document.getElementById('password_input').type = 'password';
    document.getElementById('login_mid_right_APE_eye_loginopen').style.visibility = 'hidden';
    document.getElementById('login_mid_right_APE_eye_loginclose').style.visibility = 'visible';
    // login_password = Array.from(document.getElementById('password_input').value);
    // document.getElementById('password_input').value = ('*'.repeat(login_password.length));
    // login_arraylocation = login_password.length//重製游標位置
}
function login_eyes_hidden(e) {
    document.getElementById('password_input').type = 'text'
    document.getElementById('login_mid_right_APE_eye_loginclose').style.visibility = 'hidden';
    document.getElementById('login_mid_right_APE_eye_loginopen').style.visibility = 'visible';
    // var login_passwordall = "";//密碼連起來
    // for (var i = 0; i < login_password.length; i++) {
    //     // console.log(login_passwordall[i])
    //     login_passwordall += login_password[i];
    // }
    // document.getElementById('password_input').value = login_passwordall;

    // login_arraylocation = login_password.length;
}
function registered_eyes_visible(e) {
    document.getElementById('password_input_registered').type = 'password';
    document.getElementById('login_mid_right_APE_eye_registeredopen').style.visibility = 'hidden';
    document.getElementById('login_mid_right_APE_eye_registeredclose').style.visibility = 'visible';
}
//     document.getElementById('login_mid_right_APE_eye_registeredopen').style.visibility = 'hidden';
//     document.getElementById('login_mid_right_APE_eye_registeredclose').style.visibility = 'visible';
//     registered_password = Array.from(document.getElementById('password_input_registered').value);
//     document.getElementById('password_input_registered').value = ('*'.repeat(registered_password.length));
//     registered_arraylocation = registered_password.length;
// }
function registered_eyes_hidden(e) {
    document.getElementById('password_input_registered').type = 'text';
    document.getElementById('login_mid_right_APE_eye_registeredclose').style.visibility = 'hidden';
    document.getElementById('login_mid_right_APE_eye_registeredopen').style.visibility = 'visible';
    
}
//     document.getElementById('login_mid_right_APE_eye_registeredclose').style.visibility = 'hidden';
//     document.getElementById('login_mid_right_APE_eye_registeredopen').style.visibility = 'visible';
//     var registered_passwordall = "";//密碼連起來
//     for (var i = 0; i < registered_password.length; i++) {
//         // console.log(login_password[i])
//         registered_passwordall += registered_password[i];
//     }
//     document.getElementById('password_input_registered').value = registered_passwordall;
//     registered_arraylocation = registered_password.length;
// }


//驗證帳密
function login_approve() {
    document.getElementById('login_mid_right_box_accountremind').style.visibility = 'hidden';
    document.getElementById('login_mid_right_box_passwordremind').style.visibility = 'hidden';

    //沒有輸入跳出訊息
    if ((document.getElementById('account').value.length == 0) || (document.getElementById('password_input').value.length == 0)) {
        if (document.getElementById('account').value.length == 0) {
            document.getElementById('login_mid_right_box_accountremind').style.visibility = 'visible';
        }
        if (document.getElementById('password_input').value.length == 0) {
            document.getElementById('login_mid_right_box_passwordremind').style.visibility = 'visible';
        }
        return;
    } else {
		document.getElementById('loginbutton').type="submit";
    }
    if((document.getElementById('loginbutton').type)=="button"){
    	document.getElementById('login_mid_right_APE_erro').style.visibility = 'visible';
    }
}
function registered_approve(){
    document.getElementById('registered_mid_right_box_accountremind').style.visibility = 'hidden';
    document.getElementById('registered_mid_right_box_passwordremind').style.visibility = 'hidden';

    //沒有輸入跳出訊息
    if ((document.getElementById('account_registered').value.length == 0) || (document.getElementById('password_input_registered').value.length == 0)) {
        if (document.getElementById('account_registered').value.length == 0) {
            document.getElementById('registered_mid_right_box_accountremind').style.visibility = 'visible';
            
        }
        if (document.getElementById('password_input_registered').value.length == 0) {
            document.getElementById('registered_mid_right_box_passwordremind').style.visibility = 'visible';
        }
        return;
    } else {
		document.getElementById('registered_button').type="submit";
    }
}