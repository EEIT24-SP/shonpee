package com.shonpee.shonpee.dao;



import java.util.List;

import com.shonpee.shonpee.domain.PropertyBean;

public interface  PropertyDAO {
	
    public abstract PropertyBean insert(PropertyBean bean);
	public abstract List<PropertyBean> select();
	public abstract PropertyBean update(String PropertyName,String PropertyValue);

	
	public abstract boolean delete(Integer Propertyid);
}
