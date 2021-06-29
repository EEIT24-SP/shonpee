package com.shonpee.shonpee.domain;

import lombok.Data;

@Data
public class OrderAndProductData {
	
	private ShonpeeOrderBean shonpeeOrderBean;
	private ProductBean productBean;

	// 一對一查詢，用constructor寫入
	public OrderAndProductData(ShonpeeOrderBean shonpeeOrderBean, ProductBean productBean) {
	    this.shonpeeOrderBean = shonpeeOrderBean;
	    this.productBean = productBean;
	}

}
