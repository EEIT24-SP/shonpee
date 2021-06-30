package com.shonpee.shonpee.dao;



import java.util.List;

import com.shonpee.shonpee.domain.ProductBean;


public interface ProductDAO {
	
	public abstract ProductBean insert(ProductBean bean);
	public abstract List<ProductBean> select();
	public abstract ProductBean update(String productPhoto,String productName, Integer productPrice,
			Integer productStock);
	
	

	public abstract boolean delete(Integer productid);
}
