package com.shonpee.shonpee.domain;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Data
@Table(name = "shonpee_order")
public class ShonpeeOrderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String memberId;
	private Integer productId;
	private Timestamp orderDate;
	private Integer quantity;
	private Integer total;
	private Integer payment;
	private Integer status;
	private Timestamp shippedDate;
	private Timestamp requiredDate;
	
}
