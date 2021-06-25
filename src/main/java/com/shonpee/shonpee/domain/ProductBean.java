package com.shonpee.shonpee.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productid;
	private String product_Name;
	private Integer product_Price;
	private Integer product_Stock;
	private String product_Photo;
	private String product_Detail;
	private Integer product_First_Category_Id;
	private Integer Product_Second_Category_Id;
	private Integer Product_Third_Category_Id;	
	@ManyToOne
	@JoinColumn(name = "member_Id")
	private MemberBean memberBean;
	
	@OneToMany(mappedBy = "productBean")
	private List<CartBean>  Cartlist;	
//	@OneToMany(mappedBy = "productBean",fetch = FetchType.EAGER)
//	private List<CartBean>  cartlist;	
}