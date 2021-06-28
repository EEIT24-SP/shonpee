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
	public String shoplist() {
		return "shop_list/shop_list";
	}
	@RequestMapping("main-page/shop-list-type1") 
	public String shoplist_type1() {
		System.out.println("123");
		return "shop_list/shop_list_type1";
	}
	@RequestMapping("main-page/shop-list-type2") 
	public String shoplist_type2() {
		System.out.println("123");
		return "shop_list/shop_list_type2";
	}
	@RequestMapping("main-page/shop-list-type3") 
	public String shoplist_type3() {
		System.out.println("123");
		return "shop_list/shop_list_type3";
	}
	@RequestMapping("main-page/shop-list-type4") 
	public String shoplist_type4() {
		System.out.println("123");
		return "shop_list/shop_list_type4";
	}
	@RequestMapping("main-page/shop-list-type5") 
	public String shoplist_type5() {
		System.out.println("123");
		return "shop_list/shop_list_type5";
	}
	@RequestMapping("main-page/shop-list-type6") 
	public String shoplist_type6() {
		System.out.println("6");
		return "shop_list/shop_list_type6";
	}
	
}
