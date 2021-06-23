package com.shonpee.shonpee;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class PublicController {
	
	
	@RequestMapping("/main-page")
	public String test(HttpSession session) {
	return "main";
}

	@RequestMapping("/main/card")
	public String card() {
		return "account/credit_card";
	}

	@RequestMapping("/main/address")
	public String address() {
		return "account/address";
	}

	@RequestMapping("/main/password")
	public String password() {
		return "account/password";
	}
	@RequestMapping("/main/profile")
	public String profile() {
		return "account/profile";
	}

//	@RequestMapping("/main-page/item")
//	public String item() {
//		return "Bear-item";
//	}
	
}
