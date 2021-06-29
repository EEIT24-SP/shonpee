package com.shonpee.shonpee.servicerepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.PropertyBean;
import com.shonpee.shonpee.domain.PropertyBeanSecond;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.PropertyRepository;
import com.shonpee.shonpee.repository.PropertySecondRepository;

@Service
@Transactional
public class ProductServiceRepository {

	@Autowired
	private ProductRepository productDao = null;

	@Autowired
	private PropertyRepository propertyFirst = null;

	@Autowired
	private PropertySecondRepository propertySecond = null;
	
	public PropertyBean insertFirstProperty(PropertyBean bean) {
		PropertyBean propertyFirstresult = null;
		propertyFirstresult = propertyFirst.save(bean);
		return propertyFirstresult;
	}

	public PropertyBeanSecond insertSecondProperty(PropertyBeanSecond bean) {
		PropertyBeanSecond propertySecondresult = null;
		propertySecondresult = propertySecond.save(bean);
		return propertySecondresult;
	}

	public ProductBean insert(ProductBean bean) {
		ProductBean productBeanresult = null;
		productBeanresult = productDao.save(bean);
		System.out.println("我是insert"+ bean.getMemberBean().getUserAccount());
		return productBeanresult;
	}

}
