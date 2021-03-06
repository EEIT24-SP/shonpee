package com.shonpee.shonpee.servicerepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.shonpee.shonpee.domain.PropertyBeanSecond;
import com.shonpee.shonpee.repository.PropertySecondRepository;
@Service
@Transactional
public class PropertySecondServiceRepository {
	 @Autowired
	    private PropertySecondRepository secondRepository = null;
	 
	    @Transactional(readOnly = true)
		public List<PropertyBeanSecond> select(PropertyBeanSecond bean) {
			List<PropertyBeanSecond> result = null;
			if(bean!=null && bean.getProductBean().getProductid()!=null && !bean.getProductBean().getProductid().equals(0)) {
				Optional<PropertyBeanSecond> optional2= secondRepository.findById(bean.getProductBean().getProductid());
				if(!optional2.isEmpty()) {
					PropertyBeanSecond temp2 =optional2.get();
					result= new ArrayList<PropertyBeanSecond>();
					result.add(temp2);
				}
			} else {
				result = secondRepository.findAll(); 
			}
			return result;
		}
		
		public PropertyBeanSecond update(PropertyBeanSecond bean) {
			PropertyBeanSecond result = null;
			if(bean!=null && bean.getProductBean().getProductid()!=null) {
				 if(secondRepository.existsById(bean.getPropertyid())) {
					  result = secondRepository.save(bean);
				  }
			}
			return result;
		}
		public boolean delete(PropertyBeanSecond bean) {
			boolean result = false;
			if(bean!=null && bean.getProductBean().getProductid()!=null) {
				if(secondRepository.existsById(bean.getPropertyid())) {
					  secondRepository.delete(bean); 
					  result = true;
				  }
			}
			return result;
		}
		
}
