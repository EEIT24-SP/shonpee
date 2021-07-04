//package com.shonpee.shonpee.respository;
//
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.shonpee.shonpee.domain.MemberBean;
//import com.shonpee.shonpee.domain.ProductBean;
//import com.shonpee.shonpee.repository.ProductRepository;
//import com.shonpee.shonpee.servicerepository.ProductServiceRepository;
//
//@SpringBootTest
//public class ProductServiceRepositoryTests {
//	@Test
//	void contextTest() {
//	}
//
//	@Autowired
//	private ProductServiceRepository productServiceRepository;
//	@Autowired
//	private ProductRepository PB;
//	
//	
//	@Test
//	public void testSelect() {
////		List<ProductBean>list = PB.findAll();
////		for(ProductBean productBean:list) {
////			
////			productBean.getMemberBean().getUserAccount();
////			System.out.println(			productBean.getMemberBean().getUserAccount());
////		}
//		
//		
//		ProductBean bean = new ProductBean();
////		bean.setProductId(null);
//		MemberBean mb =new MemberBean();
//		bean.setProductName("自動增長?");
//		bean.setProductPrice(123);
//		bean.setProductStock(2);;
//		bean.setProductPhoto("testpic");
//		bean.setProductDetail("cry");
//		mb.setUserAccount("anna38");
//		bean.setMemberBean(mb);
////		bean.setMemberId("anna38");
//		bean.setProductFirstCategoryId(1);;
//		bean.setProductSecondCategoryId(2);
//		bean.setProductThirdCategoryId(3);
//		PB.save(bean);
//		System.out.println("asd");
//	
//		ProductBean insert = productServiceRepository.insert(bean);
//		System.out.println("insert="+insert);
//	}
//}	
