package com.shonpee.shonpee.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Entity
@Table(name="product")
@Data
public class ProductBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ProductId;
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
