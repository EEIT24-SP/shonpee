package com.shonpee.shonpee.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.PropertyBean;

@Service
@Transactional
public class ProductServiceRepository {

	@Autowired
	private ProductRepository productDao = null;
	
	@Autowired
	private PropertyRepository property = null;
	
	
	public PropertyBean insert(PropertyBean bean) {
		PropertyBean Propertyresult = null;
		Propertyresult = property.save(bean);
		return Propertyresult;
	}

	public ProductBean insert(ProductBean bean) {
		ProductBean ProductBeanresult = null;
		ProductBeanresult = productDao.save(bean);
		return ProductBeanresult;
	}
	
	
	
	
	
	
	
	
}
