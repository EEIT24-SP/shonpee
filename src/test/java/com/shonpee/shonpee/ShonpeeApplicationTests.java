package com.shonpee.shonpee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.shonpee.shonpee.repository.CartRepository;
import com.shonpee.shonpee.repository.MemberRepository;
import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.MemberBean;

@SpringBootTest
class ShonpeeApplicationTests {

	@Autowired
	MemberRepository MR;
	@Autowired
	CartRepository CR;
	
	@Test
	void contextLoads() {
	}

//	@Test
//	public void profile1() {
//		List<MemberBean> list2 = MR.findAll();
//		for (MemberBean customerBean : list2) {
//			System.out.println(customerBean);
//		}
//	}

	@Test
	public void cart() {
//		List<MemberBean> list = MR.findAll();
//		for (MemberBean MemberBean : list) {
//			System.out.println(MemberBean);
//		List<MemberBean> list = MR.findAll();
//		for (MemberBean cartBean : list) {
//			System.out.println(cartBean.getUser_Acc());
//		}
//			List<ProductBean> list2 = PR.findAll();
//			for (ProductBean cartBean : list2) {
//				System.out.println(cartBean.getName());
//				System.out.println(cartBean.getMemberBean().getUser_Account());
//
//				
////		cartBean.setQuantity(1);
////		CR.save(cartBean);
//				
//			}
//		}
	}

}
