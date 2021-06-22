package com.shonpee.shonpee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shonpee.shonpee.Repository.MemberRepository;
import com.shonpee.shonpee.domain.MemberBean;

@Controller
public class AccountController {

	@Autowired
	MemberRepository MR;

	@RequestMapping(value = ("/main/acc"))
	public String profile1(HttpSession session, Model model, MemberBean MB) {
		if( session.getAttribute("UserName") ==null) {
			
			return "login";
		}else {
			
			List<MemberBean> list = MR.findAll();
			for (MemberBean memberBean : list) {
				if (memberBean.getUser_Account().equals(session.getAttribute("UserName"))) {
					model.addAttribute("acc", memberBean);
					session.setAttribute("accid", memberBean.getMember_Id());
				}
			}
			return "account/profile";
		}
			
			
			
			
		}
		
		

	@PostMapping(value = ("/main/acc"))
	public String test2(@ModelAttribute MemberBean MB, HttpSession session, Model model) {
		List<MemberBean> list = MR.findAll();
		for (MemberBean memberBean : list) {
			if (memberBean.getUser_Account().equals(session.getAttribute("UserName"))) {
				System.out.println(memberBean);
				MB.setMember_Id(memberBean.getMember_Id());
				MB.setPassword(memberBean.getPassword());
				MR.save(MB);
			}
		}
		return "redirect:/main/acc";
	}

}
