		package com.shonpee.shonpee.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "shonpee_order")
public class OrderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String memberId;
	private String orderImg;
	private Timestamp orderDate;
	private Integer quantity; 
	private Integer total;
	private Integer payment;
	private Integer status;
	private Timestamp shippedDate;
	private Timestamp requiredDate;
	private String typeValue;
	
	@ManyToOne
	@JoinColumn(name = "product_Id")
	private ProductBean productBean;
}
