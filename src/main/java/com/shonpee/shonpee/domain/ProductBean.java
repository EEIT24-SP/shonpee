package com.shonpee.shonpee.domain;

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
	private Integer Productid;
	private String productName;
	private Integer productPrice;
	private Integer productStock;
	private String productPhoto;
	private String productDetail;
	private String memberId;
	private Integer productFirstCategoryId;
	private Integer productSecondCategoryId;
	private Integer productThirdCategoryId;


}
