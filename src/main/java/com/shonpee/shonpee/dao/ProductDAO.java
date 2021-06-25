package com.shonpee.shonpee.dao;



import java.util.List;

import com.shonpee.shonpee.domain.ProductBean;


public interface ProductDAO {
	
	public abstract ProductBean insert(ProductBean bean);
	public abstract List<ProductBean> select();
	public abstract ProductBean update(String ProductPhoto,String ProductName, Integer ProductPrice,
			Integer ProductStock);
	
	

	public abstract boolean delete(Integer Productid);
}
