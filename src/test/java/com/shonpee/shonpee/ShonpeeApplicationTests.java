package com.shonpee.shonpee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.shonpee.shonpee.Repository.MemberRepository;
import com.shonpee.shonpee.domain.MemberBean;

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
