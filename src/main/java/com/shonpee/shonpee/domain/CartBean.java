package com.shonpee.shonpee.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "Cart")
@Data
public class CartBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cart_Id;
	private Integer quantity;
	private String 	member_Id;
	@ManyToOne
	@JoinColumn(name = "product_Id")
	private ProductBean productBean;

}
