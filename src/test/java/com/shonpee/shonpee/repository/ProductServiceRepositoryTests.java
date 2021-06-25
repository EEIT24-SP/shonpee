package com.shonpee.shonpee.repository;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.repositoryservice.ProductServiceRepository;

@SpringBootTest
public class ProductServiceRepositoryTests {
	@Test
	void contextTest() {
	}

	@Autowired
	private ProductServiceRepository productServiceRepository;
	
	
	
	@Test
	public void testSelect() {
//		ProductBean bean = new ProductBean();
//		bean.setProductid(8);
//		bean.setProductName("假秋雄");
//		bean.setProductPrice(100000);
//		bean.setProductStock(1);;
//		bean.setProductPhoto("testpic.fakejpoungg");
//		bean.setProductDetail("crycrycry~ ");
//		bean.setMemberId("bee567");
//		bean.setProductFirstCategoryId(2);;
//		bean.setProductSecondCategoryId(7);
//		bean.setProductThirdCategoryId(36);
		
            ProductBean bean = null;
		//	ProductBean insert= productServiceRepository.insert(bean);
		List<ProductBean> select= productServiceRepository.select(bean);
            for(ProductBean beans :select) {
			System.out.println(beans);
            }
//		ProductBean update= productServiceRepository.update(bean);
//		
//		boolean delete= productServiceRepository.delete(bean);
//		System.out.println("insert="+insert);
//		System.out.println("update="+update);
//		System.out.println("select="+select);
//		System.out.println("delete="+delete);
	}
	
}	
