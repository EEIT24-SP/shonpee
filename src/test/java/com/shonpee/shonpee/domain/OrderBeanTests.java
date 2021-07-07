package com.shonpee.shonpee.domain;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderBeanTests {

	@Test
	void contextLoads() {
		System.out.println("Spring容器建立了");
	}
	
	@PersistenceContext
	private Session session;
	
	@Test
	public void selectTest() {
		OrderBean aOrder = session.get(OrderBean.class, 1);
	}
	
}
