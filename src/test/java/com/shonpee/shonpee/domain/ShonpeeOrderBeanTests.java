package com.shonpee.shonpee.domain;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShonpeeOrderBeanTests {

	@Test
	void contextLoads() {
		System.out.println("Spring容器建立了");
	}
	
	@PersistenceContext
	private Session session;
	
	@Test
	public void selectTest() {
		ShonpeeOrderBean aOrder = session.get(ShonpeeOrderBean.class, 1);
		System.out.println("此訂單為 : " + aOrder);
	}
	
}
