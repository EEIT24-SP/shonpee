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
	
	
}
