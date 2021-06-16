package com.shonpee.shonpee.entity;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SellerTests {

	@Test
	void contextLoads() {
	}
	
	@PersistenceContext
	private Session session;
	
	@Test
	public void testSelect() {
		Seller select = session.get(Seller.class, 1);
		
		System.out.println("SellerTest: select = " + select);
	}
	
}
