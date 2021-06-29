package com.shonpee.shonpee;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.Productcategory;
import com.shonpee.shonpee.repository.CartRepository;




@Controller
public class PublicController {
	
	@Autowired
	CartRepository CR;
	
	@RequestMapping("/main-page/card")
	public String card() {
		return "account/credit_card";
	}

	@RequestMapping("/main-page/address")
	public String address() {
		return "account/address";
	}

	@RequestMapping("/main-page/password")
	public String password() {
		return "account/password";
	}
	@RequestMapping("/main-page/profile")
	public String profile() {
		return "account/profile"; 
	}
	
	@RequestMapping("main-page/shop-list")
	public String shoplist(Model model) {
		String[] abc ={"全部","待付款","待出貨","待收貨","完成","不成立"};
		model.addAttribute("abc",abc);
		return "shop_list/shop_list";
	}
	@GetMapping("main-page/shop-list-type{index}")
	public String ddd(@PathVariable("index") String index) {
		System.out.println(index);
		return "shop_list/shop_list";
	}	
}
