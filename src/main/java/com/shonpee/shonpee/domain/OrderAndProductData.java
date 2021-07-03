package com.shonpee.shonpee.domain;

import lombok.Data;

@Data
public class OrderAndProductData {
	
	private OrderBean orderBean;
	private ProductBean productBean;

	// 一對一查詢，用constructor寫入
	public OrderAndProductData(OrderBean shonpeeOrderBean, ProductBean productBean) {
	    this.orderBean = orderBean;
	    this.productBean = productBean;
	}

}
