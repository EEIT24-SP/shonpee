package com.shonpee.shonpee.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shonpee.shonpee.repository.ShonpeeOrderRepository;

public class OrderAndProductDataTests {
	@Autowired
	private ShonpeeOrderRepository shonpeeOrderRepository;
	
	@Test
	void contextLoads() {
		System.out.println("Spring容器建立了");
	}
	
	// 取出值為null，有空再想要怎麼改寫
//	@Test
//	void printOrderAndProduct() {
//		Integer statusNum = new Integer(1);
//		List<OrderAndProductData> status1Orders = shonpeeOrderRepository.findOrderAndProductDatas(statusNum);
//		System.out.println("=================");
//		System.out.println("訂單和產品 : " + status1Orders);
//		System.out.println("=================");
//	}
	
}
