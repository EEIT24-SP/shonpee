package com.shonpee.shonpee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shonpee.shonpee.domain.Seller;
import com.shonpee.shonpee.repositoryservice.SellerService;

@Controller
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	@GetMapping("/shopProfile")
	public String showMySeller(Model model) throws Exception  {
		// 取得會員id。前提：會員已通過登入驗證，為登入成功狀態
		Seller seller = sellerService.findMeAsSeller(); // 1應取代為取得的MemberID
		model.addAttribute("seller", seller);
		System.out.println(model.getAttribute("seller"));
		System.out.println("成功3");
		return "shopProfile";
	}
	
	@PutMapping("/seller/shopprofile")
	public ModelAndView updateSeller(Model model) {
		return null;
	}
	
}
