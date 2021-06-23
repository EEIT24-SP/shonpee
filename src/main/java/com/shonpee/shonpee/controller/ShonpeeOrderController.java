package com.shonpee.shonpee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shonpee.shonpee.repository.ShonpeeOrderRepository;

@Controller
public class ShonpeeOrderController {

	@Autowired
	private ShonpeeOrderRepository shonpeeOrderRepository;
	
	@GetMapping("/mysales")
	public String showSellerOrders(Model model) {
		return "seller/MySales";
	}
	
}
