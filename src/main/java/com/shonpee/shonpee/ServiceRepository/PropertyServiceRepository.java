package com.shonpee.shonpee.ServiceRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shonpee.shonpee.domain.PropertyBean;
import com.shonpee.shonpee.repository.PropertyRepository;

@Service
@Transactional
public class PropertyServiceRepository  {

    @Autowired
    private PropertyRepository property = null;
    @Transactional(readOnly = true)
	public List<PropertyBean> select(PropertyBean bean) {
		List<PropertyBean> result = null;
		if(bean!=null && bean.getProductid1()!=null && !bean.getProductid1().equals(0)) {
			Optional<PropertyBean> optional1= property.findById(bean.getProductid1());
			if(!optional1.isEmpty()) {
				PropertyBean temp1 =optional1.get();
				result= new ArrayList<PropertyBean>();
				result.add(temp1);
			}
		} else {
			result = property.findAll(); 
		}
		return result;
	}
	
	public PropertyBean update(PropertyBean bean) {
		PropertyBean result = null;
		if(bean!=null && bean.getProductid1()!=null) {
			 if(property.existsById(bean.getPropertyid())) {
				  result = property.save(bean);
			  }
		}
		return result;
	}
	public boolean delete(PropertyBean bean) {
		boolean result = false;
		if(bean!=null && bean.getProductid1()!=null) {
			if(property.existsById(bean.getPropertyid())) {
				  property.delete(bean); 
				  result = true;
			  }
		}
		return result;
	}
	
}
