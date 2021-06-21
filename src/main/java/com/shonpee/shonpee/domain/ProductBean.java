package com.shonpee.shonpee.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Table(name="product")
@Data
public class ProductBean {
//	@OneToOne(
//			mappedBy = "product",
//			cascade = {CascadeType.REMOVE})
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Productid;
	private String ProductName;
	private Integer ProductPrice;
	private Integer ProductStock;
	private String ProductPhoto;
	private String ProductDetail;
	private String SellerProductCategory;
	private String SellerId;
	private String ProductFirstCategoryId;
	private String ProductSecondCategoryId;
	private String ProductThirdCategoryId;
	
	
	
}
