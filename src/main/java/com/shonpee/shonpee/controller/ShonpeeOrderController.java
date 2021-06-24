package com.shonpee.shonpee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shonpee.shonpee.domain.OrderAndProductData;
import com.shonpee.shonpee.domain.ShonpeeOrderBean;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.ShonpeeOrderRepository;

@Controller
public class ShonpeeOrderController {

	@Autowired
	private ShonpeeOrderRepository shonpeeOrderRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/mysales")
	public String showSellerOrders(Model model) {
		// Status=1,表示待出貨狀態
		Integer statusNum = new Integer(1);
		
		// 找出待出貨的訂單
		List<OrderAndProductData> status1Orders = shonpeeOrderRepository.findOrderAndProductDatas(statusNum);
		System.out.println("=================");
		System.out.println("訂單和產品 : " + status1Orders);
		System.out.println("-----------------------------------");
		System.out.println("第一筆，訂單應付總額 : " + status1Orders.get(0).getShonpeeOrderBean().getTotal());
		System.out.println("-----------------------------------");
		System.out.println("第一筆，產品名稱 : " + status1Orders.get(0).getProductBean().getProductName());
		System.out.println("=================");
		
		model.addAttribute("status1Orders", status1Orders);
		return "seller/MySales";
	}
	
}
