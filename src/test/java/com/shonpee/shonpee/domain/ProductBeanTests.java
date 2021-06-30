package com.shonpee.shonpee.domain;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductBeanTests {
	@Test
	void contextLoads() {
		
	}
	
	@PersistenceContext
	private Session session;
	
	@Test
	public void testSelect() {
		ProductBean select =session.get(ProductBean.class,1);
		System.out.println("select="+select);
	}
	
}
