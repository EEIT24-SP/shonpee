//package com.shonpee.shonpee;
//
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpSession;
//
//import org.hibernate.annotations.Parameter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.shonpee.shonpee.Repository.CustomerRepository;
//import com.shonpee.shonpee.Repository.ProfileRepository;
//import com.shonpee.shonpee.domain.CustomerBean;
//import com.shonpee.shonpee.domain.Profile;
//
//
//
//@Controller
//public class PublicController {
//	@Autowired
//	private CustomerRepository CR;
//	@Autowired
//	private ProfileRepository PR;
//
//	@RequestMapping("/main/card")
//	public String card() {
//		return "account/credit_card";
//	}
//
//	@RequestMapping("/main/address")
//	public String address() {
//		return "account/address";
//	}
//
//	@RequestMapping("/main/password")
//	public String password() {
//		return "account/password";
//	}
//
//	@RequestMapping(value = ("/main/acc"))
//	public String profile1(HttpSession session, Model model, CustomerBean CB, Profile PF) {
//		List<CustomerBean> list2 = CR.findAll();
//		for (CustomerBean customerBean : list2) {
//			if (customerBean.getCustid().equals("Alex")) {
//				session.setAttribute("acc", customerBean.getCustid());
//			}
//			// 尋訪 Tcustomer 裡的 Tproduct的訊息
//			List<Profile> tp2 = customerBean.getPlist(); // 尋訪
//			for (Profile profile : tp2) {
//				if (customerBean.getCustid().equals("Alex")) {
//					model.addAttribute("PFFF", profile);
//					System.out.println("我是email" + profile.getEmail() + profile.getProid());
//				}
//			}
//		}
//		return "account/profile";
//	}
//
//	@PostMapping(value = ("/main/acc"))
//	public String test2(@ModelAttribute CustomerBean CB, Profile PF, HttpSession session, Model model) {
//		List<CustomerBean> list2 = CR.findAll();
//		for (CustomerBean customerBean : list2) {
//			// 判斷cusid符合Bear的值
//			if (customerBean.getCustid().equals(session.getAttribute("acc"))) {
//			}
//			// 尋訪 Tcustomer 裡的 Tproduct的訊息
//			List<Profile> tp2 = customerBean.getPlist(); // 尋訪
//			for (Profile profile : tp2) {
//				if (customerBean.getCustid().equals(session.getAttribute("acc"))) {
////					System.out.println(profile.getName() + profile.getEmail() + " " + profile.getYear()
////							+ customerBean.getCustid() + profile.getProid());
//					PF.setProid(profile.getProid());
//					PF.setCustomerBean(customerBean);
//					model.addAttribute("PF", PF);
//					PR.save(PF);
//				}
//			}
//		}
//		// 一旦POST方法完成而不是返回視圖名稱使用"redirect:<pageurl>"發送重定向請求。
//		return "redirect:/main/acc";
//	}
//}
