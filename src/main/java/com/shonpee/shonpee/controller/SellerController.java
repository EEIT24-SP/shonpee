package com.shonpee.shonpee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shonpee.shonpee.entity.Seller;
import com.shonpee.shonpee.repository.SellerRepository;

@Controller
public class SellerController {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@GetMapping("/seller/shopprofile")
	public ModelAndView showMySeller() {
		// 取得會員id。前提：會員已通過登入驗證，為登入成功狀態
		
		Seller meSeller = sellerRepository.findById(1);
		ModelAndView modelAndView = new ModelAndView("seller/shopprofile");
		modelAndView.addObject("shopprofile", meSeller);
		return modelAndView;
	}
	

	
}
