package com.shonpee.shonpee.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "shonpee_order")
@Data
public class OrderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String memberId;
	private String orderImg;
	private Date orderDate;
	private Integer quantity;
	private Integer total;
	private Integer payment;
	private Integer status;
	private Date shippedDate;
	private Date requiredDate;
	private String typeValue;

	@ManyToOne
	@JoinColumn(name = "product_Id")
	private ProductBean productBean;
}
