package com.shonpee.shonpee.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shonpee.shonpee.repository.OrderRepository;

public class OrderAndProductDataTests {
	@Autowired
	private OrderRepository shonpeeOrderRepository;
	
	@Test
	void contextLoads() {
	}
	
	// 取出值為null，有空再想要怎麼改寫
//	@Test
//	void printOrderAndProduct() {
//		Integer statusNum = new Integer(1);
//		List<OrderAndProductData> status1Orders = shonpeeOrderRepository.findOrderAndProductDatas(statusNum);
//	}
	
}
