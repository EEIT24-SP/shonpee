package com.shonpee.shonpee;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shonpee.shonpee.domain.MemberBean;
import com.shonpee.shonpee.repository.MemberRepository;

@SpringBootTest
class ShonpeeApplicationTests {

	@Autowired
	MemberRepository CR;

	@Test
	void contextLoads() {
		System.out.println("asdasdasd");
	}
	
	
	@Test
	public void profile1() {
		List<MemberBean> list2 = CR.findAll();
		for (MemberBean customerBean : list2) {
			System.out.println(customerBean);
		}
	}
}
