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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productid;
	private String productName;
	private Integer productPrice;
	private Integer productStock;
	private String productPhoto;
	private String productDetail;
	private Integer productFirstCategoryId;
	private Integer productSecondCategoryId;
	private Integer productThirdCategoryId;	
	
	@ManyToOne 
	@JoinColumn(name = "member_id")
	private MemberBean memberBean;
	
	@OneToMany(mappedBy = "productBean")
	private List<CartBean>  cartlist;	
	
	@OneToMany(mappedBy = "productBean")
	private List<PropertyBean>  prpBean;	
	
	@OneToMany(mappedBy = "productBean")
	private List<PropertyBeanSecond>  prpBeanSecond;

	
//	@OneToMany(mappedBy = "productBean",fetch = FetchType.EAGER)
//	private List<CartBean>  cartlist;	

}
