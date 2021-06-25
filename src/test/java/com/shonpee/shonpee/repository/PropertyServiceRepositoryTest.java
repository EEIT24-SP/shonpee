package com.shonpee.shonpee.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shonpee.shonpee.domain.PropertyBean;
import com.shonpee.shonpee.domain.PropertyBeanSecond;
import com.shonpee.shonpee.repository.PropertySecondRepository;
import com.shonpee.shonpee.repositoryservice.PropertySecondServiceRepository;
import com.shonpee.shonpee.repositoryservice.PropertyServiceRepository;




@SpringBootTest
public class PropertyServiceRepositoryTest {
	
	@Test
	void contextTest() {
	}
	
	@Autowired
	private PropertyServiceRepository propertyServiceRepository;
	@Autowired
	private PropertySecondServiceRepository propertySecondeServiceRepository;
	
	
	@Test
	public void testproperty() {
//		PropertyBean pBean= new PropertyBean();
//		pBean.setProductid1(3);
//		pBean.setPropertyid(3);
//		pBean.setPropertyName("color");
//		pBean.setPropertyValue("orange");
		
		PropertyBean pBean=null;
		List<PropertyBean> select= propertyServiceRepository.select(pBean);
		for(PropertyBean pbean:select) {
			System.out.println(pbean);
		}
		
		PropertyBeanSecond p2bean= null;
		List<PropertyBeanSecond> select2 = propertySecondeServiceRepository.select(p2bean);
		for(PropertyBeanSecond pbean2:select2) {
			System.out.println(pbean2);
		}
//		boolean delete= propertyServiceRepository.delete(pBean);
//		PropertyBean update= propertyServiceRepository.update(pBean);
//		System.out.println("select="+select);
//		System.out.println("update="+update);
//		System.out.println("delete="+delete);
		
		
	}
	
}
