package com.shonpee.shonpee;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shonpee.shonpee.ServiceRepository.MemberServiceRepository;
import com.shonpee.shonpee.domain.MemberBean;

@Controller
public class LoginController {
	@Autowired
	private MemberServiceRepository memberService;
 
	@RequestMapping(value = "/login-page") 
	public String login(HttpSession session) {
		session.setAttribute("UserName","");
		return "login";
	}

	@PostMapping(value = ("/login-page"))
	public String control(String loginaccount, String loginpassword, String registered_account,
			String registered_password, HttpSession session, Model model) {
		
		if ((loginaccount != null) && (loginpassword != null)) {
			// 呼叫model
			MemberBean beanLogin = memberService.login(loginaccount, loginpassword);
			if (beanLogin == null) {
				return "login";
			} else {
//				System.out.println(session.getAttribute("user"));
				session.setAttribute("UserName", beanLogin.getUserAccount());
				return "redirect:/main-page";
			}
		} else if ((registered_account != null) && (registered_password != null)) {
			// 呼叫model
			MemberBean beanRegistered = memberService.registered_member(registered_account, registered_password);
			if (beanRegistered == null) {
//				model.addAttribute("erro","帳號或密碼已被註冊");
				return "login"; 
			} else {
				session.setAttribute("user", beanRegistered);
				return "login";
			}
		} else {
			return "login";
		}
	}
}
