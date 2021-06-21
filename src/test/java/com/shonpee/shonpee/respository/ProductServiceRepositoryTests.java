package com.shonpee.shonpee.respository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.repository.ProductServiceRepository;

@SpringBootTest
public class ProductServiceRepositoryTests {
	@Test
	void contextTest() {
	}

	@Autowired
	private ProductServiceRepository productServiceRepository;
	
	
	
	@Test
	public void testSelect() {
		ProductBean bean = new ProductBean();
//		bean.setProductId(null);
		bean.setProductName("自動增長?");
		bean.setProductPrice(123);
		bean.setProductStock(2);;
		bean.setProductPhoto("testpic");
		bean.setProductDetail("cry");
		bean.setSellerProductCategory("還沒做");
		bean.setSellerId("allen3375");;
		bean.setProductFirstCategoryId("1");;
		bean.setProductSecondCategoryId("2");
		bean.setProductThirdCategoryId("3");
		
		ProductBean insert = productServiceRepository.insert(bean);
		System.out.println("insert="+insert);
	}
}	
