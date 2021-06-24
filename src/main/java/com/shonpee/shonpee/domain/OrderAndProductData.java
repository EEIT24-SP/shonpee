package com.shonpee.shonpee.domain;

import lombok.Data;

@Data
public class OrderAndProductData {
	private ShonpeeOrderBean shonpeeOrderBean;
	private ProductBean productBean;
	
	public OrderAndProductData(ShonpeeOrderBean shonpeeOrderBean, ProductBean productBean) {
	    this.shonpeeOrderBean = shonpeeOrderBean;
	    this.productBean = productBean;
	}
}
