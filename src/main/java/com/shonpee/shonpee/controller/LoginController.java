package com.shonpee.shonpee.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shonpee.shonpee.domain.MemberBean;
import com.shonpee.shonpee.servicerepository.MemberServiceRepository;

@Controller
public class LoginController {
	@Autowired
	private MemberServiceRepository memberService;
	
	@RequestMapping(value = "/login")
	public String control(String loginaccount, String loginpassword,
			String registered_account, String registered_password,HttpSession session,Model model) {

		if((loginaccount!=null)&&(loginpassword!=null)) {
			//呼叫model
			MemberBean beanLogin = memberService.login(loginaccount, loginpassword);
			if(beanLogin==null) {
				System.out.println("gg");
				return "login";
			}else {					
//				System.out.println("oooooo");
				session.setAttribute("user", beanLogin);
				model.addAttribute("data","登入");
				model.addAttribute("session", "user");
				System.out.println(session.getAttribute("user"));
				session.setAttribute("UserName",beanLogin.getUser_Account());
				System.out.println(session.getAttribute("UserName"));
				return "redirect:/main";
			}
//			model.addAttribute("data","登入");
//			return "test";			
		}else if((registered_account!=null)&&(registered_password!=null)) {
			//呼叫model
			MemberBean beanRegistered = memberService.registered_member(registered_account, registered_password);
			if(beanRegistered==null) {
				System.out.println("qweqeqeqweqeqweqeqeqweqweqwe");

//				model.addAttribute("erro","帳號或密碼已被註冊");
				return "login";
			}else {					
				session.setAttribute("user", beanRegistered);
				model.addAttribute("data2","註冊");
				System.out.println("sdsdsdsddsdsdsd");

				return "test2";
			}				
		}else{
			return "login";
		}	
		
	}
//	@RequestMapping(value ="/test")
//	public String test(HttpSession session) {
//		System.out.println(	"zzzzzzzzzzz");	
//		System.out.println(session.getAttribute("user"));	
//		return "test";
//	}
}