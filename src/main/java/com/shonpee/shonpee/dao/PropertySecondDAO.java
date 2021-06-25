package com.shonpee.shonpee.dao;


import java.util.List;

import com.shonpee.shonpee.domain.PropertyBeanSecond;

public interface PropertySecondDAO {
	    public abstract PropertyBeanSecond insert(PropertyBeanSecond bean);
		public abstract List<PropertyBeanSecond> select();
		public abstract PropertyBeanSecond update(String PropertyName,String PropertyValue);

		
		public abstract boolean delete(Integer Propertyid);
}
