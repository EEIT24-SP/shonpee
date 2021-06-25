package com.shonpee.shonpee.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "shonpee_order")
@Data
public class OrderBean {
	
	@Id
	private int Order_id;
	private String Member_id;
	private int Product_id;
	private Date Order_date;
	private	int Quantity;
	private int Total;
	private int Payment;
	private int Status;
	private Date Shipped_date;
	private Date Required_date;
}
