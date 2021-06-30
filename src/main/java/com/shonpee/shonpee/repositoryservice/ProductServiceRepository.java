package com.shonpee.shonpee.repositoryservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private PropertyRepository property = null;
	
	@Autowired
	private PropertySecondRepository propertySecond = null;
	
	
	public PropertyBean insertFirstProperty(PropertyBean bean) {
		PropertyBean Propertyresult = null;
		Propertyresult = property.save(bean);
		return Propertyresult;
	}

	public PropertyBeanSecond insertSecondProperty(PropertyBeanSecond bean) {
		PropertyBeanSecond PropertySecondresult = null;
		PropertySecondresult = propertySecond.save(bean);
		return PropertySecondresult;
	}

	
	public ProductBean insert(ProductBean bean) {
		ProductBean ProductBeanresult = null;
		ProductBeanresult = productDao.save(bean);
		return ProductBeanresult;
	}
	
	 @Transactional(readOnly = true)
	public List<ProductBean> select(ProductBean bean) {
		 List<ProductBean> ProductBeanresult = null;
		if(bean!=null && bean.getProductid()!=null && !bean.getProductid().equals(0)) {
			Optional<ProductBean> optional= productDao.findById(bean.getProductid());
			if(!optional.isEmpty()) {
				ProductBean temp =optional.get();
				 ProductBeanresult=new ArrayList<ProductBean>();
				 ProductBeanresult.add(temp);
				
			}
		} else {
			ProductBeanresult = productDao.findAll(); 
		}
		return ProductBeanresult;
	}
	
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null && bean.getProductid()!=null) {
			 if(productDao.existsById(bean.getProductid())) {
				  result = productDao.save(bean);
			  }
		}
		return result;
	}
	
	public boolean delete(ProductBean bean) {
		boolean result = false;
		if(bean!=null && bean.getProductid()!=null) {
			if(productDao.existsById(bean.getProductid())) {
				  productDao.delete(bean); 
				  result = true;
			  }
		}
		return result;
	}
}
